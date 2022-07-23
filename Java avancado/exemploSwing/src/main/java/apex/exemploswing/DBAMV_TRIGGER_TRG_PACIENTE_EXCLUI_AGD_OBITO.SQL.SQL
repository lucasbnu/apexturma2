PROMPT CREATE OR REPLACE TRIGGER dbamv.trg_paciente_exclui_agd_obito
CREATE OR REPLACE TRIGGER dbamv.trg_paciente_exclui_agd_obito
  after update on dbamv.paciente for each row
declare

CURSOR cRegistros IS
   select it.cd_it_agenda_central
     from dbamv.it_agenda_central it
          ,dbamv.agenda_central a
    where a.cd_agenda_central = it.cd_agenda_central
      AND it.cd_paciente = :new.cd_paciente
      and nvl(it.tp_situacao,'M') in ('M','C')
      and it.cd_atendimento is NULL
      AND a.dt_agenda >= Trunc(SYSDATE-30);

 v_quimio_difer varchar2(1);

CURSOR cRegistros_Padrao IS
   select cd_it_agenda_central
     from dbamv.it_agenda_central
    where cd_paciente = :new.cd_paciente
      and nvl(tp_situacao,'M') in ('M','C')
      and cd_atendimento is NULL;

procedure prc_exclui_mov_pactuacao(pcd_it_agenda_central in number) is
    cursor cVerificaMovimento IS
      select cd_movimento_pactuacao,
             cd_tipo_pactuacao,
             cd_referencia,
             sn_efetua_baixa_saldo,
             cd_especialid,
             cd_tip_mar,
             cd_grupo_exame,
             nr_quant_baixa_saldo,
             sn_baixa_cota_realizacao
        from dbamv.movimento_pactuacao
       where cd_it_agenda_central = pCd_it_agenda_central;
    --
    vVerificaMovimento cVerificaMovimento%ROWTYPE;
    --
  begin
    --
    vVerificaMovimento := NULL;
    --
    open cVerificaMovimento;
    fetch cVerificaMovimento
      into vVerificaMovimento;
    close cVerificaMovimento;
    --
    IF vVerificaMovimento.cd_movimento_pactuacao IS NOT NULL THEN
      --
      delete dbamv.movimento_pactuacao
       where cd_movimento_pactuacao =
             vVerificaMovimento.cd_movimento_pactuacao;
      --
      if ((vVerificaMovimento.cd_especialid is null) and
         (vVerificaMovimento.cd_tip_mar is null) and
         (vVerificaMovimento.cd_grupo_exame is not null)) then
        --
        if ((vVerificaMovimento.sn_efetua_baixa_saldo = 'S') and
           (vVerificaMovimento.sn_baixa_cota_realizacao = 'N')) then
          --
          update dbamv.pactuacao_exame
             set nr_valor_utilizado = Nvl(nr_valor_utilizado, 0) -
                                      vVerificaMovimento.nr_quant_baixa_saldo
           where cd_tipo_pactuacao = vVerificaMovimento.cd_tipo_pactuacao
             and cd_referencia = vVerificaMovimento.cd_referencia
             and cd_grupo_exame = vVerificaMovimento.cd_grupo_exame;
          --
        end if;
        --
      elsif ((vVerificaMovimento.cd_especialid is not null) and
            (vVerificaMovimento.cd_tip_mar is not null) and
            (vVerificaMovimento.cd_grupo_exame IS NULL)) then
        --
        if vVerificaMovimento.sn_efetua_baixa_saldo = 'S' then
          --
          update dbamv.pactuacao_ambulatorial
             set nr_valor_utilizado = Nvl(nr_valor_utilizado, 0) -
                                      vVerificaMovimento.nr_quant_baixa_saldo
           where cd_tipo_pactuacao = vVerificaMovimento.cd_tipo_pactuacao
             and cd_referencia = vVerificaMovimento.cd_referencia
             and cd_especialid = vVerificaMovimento.cd_especialid
             and cd_tip_mar = vVerificaMovimento.cd_tip_mar;
          --
        end if;
        --
      end if;
      --
    END IF;
    --
  end prc_exclui_mov_pactuacao;



 procedure excluir (pItAgenda   In number, pTpSituacao In Varchar2 default null) is

  nCdLogExclusao              number;
  vCdItMovimentoAgendaCentral number;
  vBloqueio                   varchar2(1);

  -->>Obtém o código anterior do atendimento (avô) para desfazer o processo
  Cursor cAtendAnterior(pSolicAgenda dbamv.solic_agendamento.cd_solic_agendamento%type) Is
    Select cd_atendimento
      from dbamv.ciclo_tratamento
     where (cd_tratamento, nr_ciclo) In
           (Select cd_tratamento, nr_ciclo
              from dbamv.solic_agendamento
             where cd_solic_agendamento = pSolicAgenda);

  -- Indica se o horário está bloqueado de acordo com os registros de bloqueio em dbamv.log_opera_agenda_central
  function fnc_horario_bloqueado(p_cd_it_agenda_central in number)
    return varchar2 is

    /* função criada em 14/12/2008 por tiago calado como melhoria do sistema.

       esta função retorna s ou n, indicando se determinado horário passado como parâmetro
       deve ser marcado como bloqueado, de acordo com bloqueios já gerados.
       nesta tela, a função será utilizada na exclusão de agendamentos, para evitar que após
       uma agenda ter sido bloqueada, seja necessário efetuar outro bloqueio após a exclusão de agendamentos,
       pois esta última operação deixa os horários como livres após sua realização.

    */

    cursor c_info_agenda(pcd_it_agenda_central in number) is
      select i.cd_it_agenda_central,
             i.cd_agenda_central,
             a.tp_agenda,
             a.dt_agenda,
             i.hr_agenda,
             a.cd_prestador,
             a.cd_recurso_central,
             a.cd_unidade_atendimento,
             a.cd_setor
        from dbamv.it_agenda_central i, dbamv.agenda_central a
       where i.cd_agenda_central = a.cd_agenda_central
         and cd_it_agenda_central = pcd_it_agenda_central;

    rinfo_agenda c_info_agenda%rowtype;

    cursor c_bloqueio(p_cd_agenda_central      number,
                      p_tp_agenda              varchar2,
                      p_dt_agenda              date,
                      p_hr_agenda              date,
                      p_cd_prestador           number,
                      p_cd_recurso_central     number,
                      p_cd_unidade_atendimento number,
                      p_cd_setor               number) is
      select decode(tp_operacao, 'B', 'S', 'D', 'N', 'N') tp_operacao
        from dbamv.log_opera_agenda_central
       where cd_log_opera_agenda =
             (select max(cd_log_opera_agenda)
                from dbamv.log_opera_agenda_central
               where tp_operacao in ('B', 'D')
                 and cd_agenda_central = p_cd_agenda_central
                  or ((p_dt_agenda >= dt_inicio -1 and     -- OP 3197
                       p_dt_agenda < dt_fim and -- OP 3197
                     to_number(to_char(p_hr_agenda, 'sssss')) between
                     to_number(to_char(hr_inicio_operacao, 'sssss')) and
                     to_number(to_char(hr_fim_operacao, 'sssss') - 1)) and
                     decode(tp_agenda, 'T', p_tp_agenda, tp_agenda) =
                     p_tp_agenda and
                     (cd_prestador = p_cd_prestador or cd_prestador is null) and
                     (cd_recurso_central = p_cd_recurso_central or
                     cd_recurso_central is null) and
                     (cd_unidade_atendimento = p_cd_unidade_atendimento or
                     cd_unidade_atendimento is null) and
                     (cd_setor = p_cd_setor or cd_setor is null)));
    /*         and dbamv.pkg_central_marcacoes.Fnc_Usuario_Permissao_Setor(cd_usuario
                                                                        ,p_cd_setor
                                                                        ,p_cd_unidade_atendimento
                                                                        ) = 'S';
    */

    vtp_operacao varchar2(1);

  begin

    open c_info_agenda(p_cd_it_agenda_central);
    fetch c_info_agenda
      into rinfo_agenda;
    close c_info_agenda;

    open c_bloqueio(rinfo_agenda.cd_agenda_central,
                    rinfo_agenda.tp_agenda,
                    rinfo_agenda.dt_agenda,
                    rinfo_agenda.hr_agenda,
                    rinfo_agenda.cd_prestador,
                    rinfo_agenda.cd_recurso_central,
                    rinfo_agenda.cd_unidade_atendimento,
                    rinfo_agenda.cd_setor);
    fetch c_bloqueio
      into vtp_operacao;

    close c_bloqueio;

    return nvl(vtp_operacao, 'N');

  end fnc_horario_bloqueado;

  function Fnc_Localiza_Mov_AgendaCentral(pItAgendaCentral number)
    Return number Is

    cursor cMovimento Is
      select cd_it_movimento_agenda_central
        from dbamv.IT_MOVIMENTO_AGENDA_CENTRAL
       where Decode(tp_status,
                    'A',
                    cd_it_agenda_central,
                    'T',
                    cd_it_agenda_central_atual,
                    'C',
                    Nvl(cd_it_agenda_central, cd_it_agenda_central_atual),
                    'E',
                    Null) = pItAgendaCentral;

    cursor cMovimento_difer Is
      select    it_movimento_agenda_central.cd_it_movimento_agenda_central  cd_it_movimento_agenda_central
      from      dbamv.it_movimento_agenda_central                           it_movimento_agenda_central
      where     it_movimento_agenda_central.tp_status                       = 'A'
      and       it_movimento_agenda_central.cd_it_agenda_central            = pitagendacentral
        union all
      select    it_movimento_agenda_central.cd_it_movimento_agenda_central  cd_it_movimento_agenda_central
      from      dbamv.it_movimento_agenda_central                           it_movimento_agenda_central
      where     it_movimento_agenda_central.tp_status                       = 'T'
      and       it_movimento_agenda_central.cd_it_agenda_central_atual      = pitagendacentral
        union all
      select    it_movimento_agenda_central.cd_it_movimento_agenda_central  cd_it_movimento_agenda_central
      from      dbamv.it_movimento_agenda_central                           it_movimento_agenda_central
      where     it_movimento_agenda_central.tp_status                       = 'C'
      and       it_movimento_agenda_central.cd_it_agenda_central            = pitagendacentral;


    nMovimento cMovimento%RowType;

  Begin

    if v_quimio_difer <> 'S' then
    Open cMovimento;
    Fetch cMovimento Into nMovimento;
    Close cMovimento;
    Return nMovimento.cd_it_movimento_agenda_central;
    else
    Open cMovimento_difer;
    Fetch cMovimento_difer Into nMovimento;
    Close cMovimento_difer;
    Return nMovimento.cd_it_movimento_agenda_central;
  end if;

  End Fnc_Localiza_Mov_AgendaCentral;

  -->>Exclui os agendamentos futuros a partir de uma sessão
  procedure excluir_futuros(pItAgenda   in dbamv.it_agenda_central.cd_it_agenda_central%Type,
                            pTpSituacao In Varchar2) is
    nAtendimento Number;
  begin

    for x in (Select it_agenda_central.cd_it_agenda_central,
                     it_agenda_central.cd_solic_agendamento
                From dbamv.it_agenda_central, dbamv.agenda_central
               Where agenda_central.cd_agenda_central =
                     it_agenda_central.cd_agenda_central
                 and it_agenda_central.cd_it_agenda_pai is null
                 and it_agenda_central.cd_it_agenda_central <> pItAgenda
                 and it_agenda_central.tp_situacao is null
                 and it_agenda_central.cd_solic_Agendamento In
                     (Select cd_solic_agendamento
                        from dbamv.solic_agendamento
                       Where (cd_tratamento, nr_ciclo) in
                             (Select cd_tratamento, nr_ciclo
                                From dbamv.it_agenda_central,
                                     dbamv.solic_agendamento
                               Where it_agenda_central.cd_it_agenda_central =
                                     pItAgenda
                                 and solic_agendamento.cd_solic_agendamento =
                                     it_agenda_central.cd_solic_agendamento))
               order by agenda_central.dt_agenda) loop

      excluir(x.cd_it_agenda_central,
                                        pTpSituacao);

      Open cAtendAnterior(x.cd_solic_agendamento);
      Fetch cAtendAnterior
        into nAtendimento;
      Close cAtendAnterior;

      update dbamv.solic_agendamento
         set tp_situacao          = pTpSituacao,
             cd_atendimento       = nAtendimento,
             cd_it_agenda_central = decode(pTpSituacao,
                                           'C',
                                           cd_it_agenda_central,
                                           null)
       where cd_solic_agendamento = x.cd_solic_agendamento;

    end loop;

    --PDA 307843 (início)
    for x in (SELECT it_agenda_central.cd_it_agenda_central
                FROM dbamv.it_agenda_central, dbamv.agenda_central
               WHERE it_agenda_central.cd_agenda_central =
                     agenda_central.cd_agenda_central
                 AND it_agenda_central.cd_it_agenda_central <> pItAgenda
                 AND to_date(to_char(trunc(agenda_central.dt_agenda),
                                     'ddmmyyyy') ||
                             to_char(it_agenda_central.hr_agenda, 'hh24mi'),
                             'ddmmyyyyhh24mi') >
                     (SELECT to_date(to_char(trunc(agenda_central.dt_agenda),
                                             'ddmmyyyy') ||
                                     to_char(it_agenda_central.hr_agenda,
                                             'hh24mi'),
                                     'ddmmyyyyhh24mi')
                        FROM dbamv.it_agenda_central, dbamv.agenda_central
                       WHERE it_agenda_central.cd_agenda_central =
                             agenda_central.cd_agenda_central
                         AND it_agenda_central.cd_it_agenda_central =
                             pItAgenda)
                 AND it_agenda_central.cd_it_agenda_central IN
                     (SELECT it_movimento_agenda_central.cd_it_agenda_central
                        FROM dbamv.it_movimento_agenda_central
                       WHERE it_movimento_agenda_central.cd_movimento_agenda_central IN
                             (SELECT cd_movimento_agenda_central
                                FROM dbamv.it_movimento_agenda_central
                               WHERE tp_status in ('A', 'C')
                                 AND cd_it_agenda_central = pItAgenda))) loop

      excluir(x.cd_it_agenda_central, null);

    end loop;
    --PDA 307843 (fim)

  end excluir_futuros;

begin

  for x in (select cd_agenda_central,
                   tp_agenda,
                   cd_unidade_atendimento,
                   cd_recurso_central,
                   cd_prestador,
                   cd_setor,
                   dt_agenda,
                   hr_inicio,
                   hr_fim,
                   sn_sia,
                   qt_atendimento,
                   qt_encaixes,
                   qt_marcados,
                   qt_encaixes_marcados,
                   qt_tempo_medio,
                   tp_producao,
                   ds_consultorio,
                   dt_liberacao,
                   sn_ativo,
                   sn_falta,
                   sn_servico_livre,
                   sn_tipo_livre,
                   sn_publico_ac,
                   sn_agenda_dinamica,
                   cd_multi_empresa,
                   cd_usuario_ac,
                   cd_agenda_central_it,
                   hr_agenda,
                   cd_paciente,
                   nm_paciente,
                   vl_altura,
                   qt_peso,
                   dt_nascimento,
                   sn_atendido,
                   sn_encaixe,
                   cd_grupo_agenda,
                   cd_atendimento,
                   cd_atendimento_pai,
                   cd_gru_ate,
                   cd_item_agendamento,
                   cd_usuario,
                   cd_convenio,
                   cd_con_pla,
                   cd_ser_dis,
                   cd_tip_mar,
                   tp_situacao,
                   vl_perc_desconto,
                   vl_negociado,
                   sn_anestesista,
                   sn_publico_iac,
                   sn_bloqueado,
                   nr_fone,
                   cd_it_agenda_central,
                   cd_it_agenda_pai,
                   tp_sexo,
                   cd_anestesista,
                   ds_observacao,
                   ds_observacao_geral --??
                  ,
                   cd_solic_agendamento,
                   ds_senha_painel,
                   sn_dispensa_equipamentos
              from (SELECT agenda_central.cd_agenda_central,
                           agenda_central.tp_agenda,
                           agenda_central.cd_unidade_atendimento,
                           agenda_central.cd_recurso_central,
                           agenda_central.cd_prestador,
                           agenda_central.cd_setor,
                           agenda_central.dt_agenda,
                           agenda_central.hr_inicio,
                           agenda_central.hr_fim,
                           agenda_central.sn_sia,
                           agenda_central.qt_atendimento,
                           agenda_central.qt_encaixes,
                           agenda_central.qt_marcados,
                           agenda_central.qt_encaixes_marcados,
                           agenda_central.qt_tempo_medio,
                           agenda_central.tp_producao,
                           agenda_central.ds_consultorio,
                           agenda_central.dt_liberacao,
                           agenda_central.sn_ativo,
                           agenda_central.sn_falta,
                           agenda_central.sn_servico_livre,
                           agenda_central.sn_tipo_livre,
                           agenda_central.sn_agenda_publica           sn_publico_ac,
                           agenda_central.sn_agenda_dinamica,
                           agenda_central.cd_multi_empresa,
                           agenda_central.cd_usuario                  cd_usuario_ac,
                           it_agenda_central.cd_agenda_central        cd_agenda_central_it,
                           it_agenda_central.hr_agenda,
                           it_agenda_central.cd_paciente,
                           it_agenda_central.nm_paciente,
                           it_agenda_central.vl_altura,
                           it_agenda_central.qt_peso,
                           it_agenda_central.dt_nascimento,
                           it_agenda_central.sn_atendido,
                           it_agenda_central.sn_encaixe,
                           it_agenda_central.cd_grupo_agenda,
                           it_agenda_central.cd_atendimento,
                           it_agenda_central.cd_atendimento_pai,
                           it_agenda_central.cd_gru_ate,
                           it_agenda_central.cd_item_agendamento,
                           it_agenda_central.cd_usuario,
                           it_agenda_central.cd_convenio,
                           it_agenda_central.cd_con_pla,
                           it_agenda_central.cd_ser_dis,
                           it_agenda_central.cd_tip_mar,
                           it_agenda_central.tp_situacao,
                           it_agenda_central.vl_perc_desconto,
                           it_agenda_central.vl_negociado,
                           it_agenda_central.sn_anestesista,
                           it_agenda_central.sn_publico               sn_publico_iac,
                           it_agenda_central.sn_bloqueado,
                           it_agenda_central.nr_fone,
                           it_agenda_central.cd_it_agenda_central,
                           it_agenda_central.cd_it_agenda_pai,
                           it_agenda_central.tp_sexo,
                           it_agenda_central.cd_anestesista,
                           it_agenda_central.ds_observacao,
                           it_agenda_central.cd_solic_agendamento,
                           it_agenda_central.ds_senha_painel,
                           it_agenda_central.sn_dispensa_equipamentos,
                           it_agenda_central.sn_sessao,
                           it_agenda_central.ds_observacao_geral
                      FROM dbamv.it_agenda_central, dbamv.agenda_central
                     WHERE it_agenda_central.cd_agenda_central = agenda_central.cd_agenda_central
                    )
             where cd_it_agenda_central = pItAgenda) loop
    --
    SELECT DBAMV.SEQ_LOG_OPERA_AGENDA_CENTRAL.NEXTVAL
      into nCdLogExclusao
      FROM SYS.DUAL;
    /*********************** Inserindo o Registro de Agendamento **************************/
    INSERT INTO DBAMV.LOG_OPERA_AGENDA_CENTRAL
      (CD_LOG_OPERA_AGENDA,
       DT_OPERA_AGENDA,
       TP_OPERACAO,
       CD_AGENDA_CENTRAL,
       TP_AGENDA,
       CD_USUARIO,
       DS_MOTIVO,
       CD_GRUPO_AGENDA,
       CD_ITEM_AGENDAMENTO,
       CD_RECURSO_CENTRAL,
       CD_UNIDADE_ATENDIMENTO,
       CD_PACIENTE,
       NM_PACIENTE,
       CD_PRESTADOR,
       CD_SETOR,
       DT_AGENDA,
       CD_MULTI_EMPRESA,
       CD_IT_AGENDA_CENTRAL,
       HR_AGENDA)
    VALUES
      (nCdLogExclusao,
       SYSDATE,
       'H',
       x.CD_AGENDA_CENTRAL,
       x.TP_AGENDA,
       USER,
       'EXCLUIDO AUTOMATICAMENTE POR OBITO DO PACIENTE',
       x.CD_GRUPO_AGENDA,
       x.CD_ITEM_AGENDAMENTO,
       x.CD_RECURSO_CENTRAL,
       x.CD_UNIDADE_ATENDIMENTO,
       x.CD_PACIENTE,
       x.NM_PACIENTE,
       x.CD_PRESTADOR,
       x.CD_SETOR,
       x.DT_AGENDA,
       DBAMV.PKG_MV2000.LE_EMPRESA,
       x.CD_IT_AGENDA_CENTRAL,
       x.HR_AGENDA);

    --********************************************************************************
    update dbamv.solic_agendamento
       set cd_it_agenda_central = null, tp_situacao = pTpSituacao
     where cd_it_agenda_central = pItAgenda;
    --
    delete dbamv.per_pedlab where cd_it_agenda_central = pItAgenda;
    --
    delete dbamv.per_pedrx where cd_it_agenda_central = pItAgenda;
    --
    delete from dbamv.registro_fluxo_agenda -- AGG
    where cd_it_agenda_central = pItAgenda;
    --
    if x.sn_encaixe = 'N' then

      -- Realiza o bloqueio dos hor¿rios
      for i in (Select cd_it_agenda_central
                  from dbamv.it_agenda_central
                 where (cd_it_agenda_central = pItAgenda or
                       cd_it_agenda_pai = pItAgenda)) loop

        vbloqueio := fnc_horario_bloqueado(i.cd_it_agenda_central);

        update dbamv.it_agenda_central
           set sn_bloqueado = vbloqueio
         where cd_it_agenda_central = i.cd_it_agenda_central;

      end loop;
      --

      update dbamv.it_agenda_central
         set cd_paciente                                = NULL,
             nm_paciente                                = null,
             vl_altura                                  = null,
             qt_peso                                    = null,
             dt_nascimento                              = null,
             sn_atendido                                = 'N',
             sn_encaixe                                 = 'N',
             cd_grupo_agenda                            = null,
             cd_gru_ate                                 = null,
             cd_item_agendamento                        = null,
             cd_usuario                                 = null,
             cd_convenio                                = null,
             cd_con_pla                                 = null,
             cd_ser_dis                                 = null,
             cd_tip_mar                                 = null,
             tp_situacao                                = null,
             vl_perc_desconto                           = null,
             vl_negociado                               = null,
             sn_anestesista                             = 'N',
             nr_fone                                    = null,
             cd_it_agenda_pai                           = null,
             tp_sexo                                    = null,
             cd_anestesista                             = null,
             ds_observacao                              = null,
             ds_observacao_geral                        = null --??
            ,
             cd_solic_agendamento                       = null,
             cd_atendimento_pai                         = null,
             cd_agenda_fila_espera                      = null,
             sn_sessao                                  = 'N',
             dt_gravacao                                = Null,
             it_agenda_central.sn_dispensa_equipamentos = null,
             DS_EMAIL                                   = null,
             DS_SENHA_PAINEL                            = null,
             NR_SESSAO_SCMA                             = null,
             cd_atendimento                             = null,
             hr_fim                                     = null -- PDA 219612
            ,
             vl_tempo_realizacao_informado              = null --PDA 207614
       where cd_it_agenda_central IN
             (Select cd_it_agenda_central
                from dbamv.it_agenda_central
               where (cd_it_agenda_central = pItAgenda or
                     cd_it_agenda_pai = pItAgenda));

    else

     prc_exclui_mov_pactuacao(pItAgenda); --PDA..: 480314

      delete from dbamv.it_agenda_central
       where cd_it_agenda_central = pItAgenda;
    end if;
    --******************************************************************************************************************
    delete dbamv.agenda_central_gru_agen_pac
     where cd_it_agenda_central = pItAgenda;
    --
    vCdItMovimentoAgendaCentral := Fnc_Localiza_Mov_AgendaCentral(pItAgenda);
    --
    update dbamv.it_movimento_agenda_central
       set tp_status = 'E', cd_it_agenda_central_atual = null
     where cd_it_movimento_agenda_central = vCdItMovimentoAgendaCentral;
    --
    delete from dbamv.it_agenda_central_equipto
     where it_agenda_central_equipto.cd_it_agenda_central = pItAgenda;
    --
    if x.sn_encaixe = 'S' then
      update dbamv.agenda_central
         set qt_encaixes_marcados =
             (qt_encaixes_marcados - 1)
       where cd_agenda_central = x.CD_AGENDA_CENTRAL;
    else
      update dbamv.agenda_central
         set qt_marcados =
             (qt_marcados - 1)
       where cd_agenda_central = x.CD_AGENDA_CENTRAL;
    end if;
  end loop;

END excluir;

begin

 if :new.tp_situacao = 'O' THEN
    --
    IF Nvl(dbamv.pkg_mv2000.le_configuracao('SCMA','SN_ATIVA_JOB_ALTA_OBITO'),'N') = 'S' THEN
       --
       INSERT INTO dbamv.paciente_obito(cd_paciente_obito,
                                        cd_paciente,
                                        dt_criacao,
                                        cd_usuario,
                                        tp_status) VALUES(seq_paciente_obito.nextval,
                                                          :NEW.cd_paciente,
                                                          SYSDATE,
                                                          dbamv.Pkg_Mv_Variaveis.fnc_get_usuario,
                                                          'A');
       --
    ELSE
       --
       dbamv.prc_scma_agd_obito(:NEW.cd_paciente);
       --
    END IF;
    --
 end if;


end trg_paciente_exclui_agd_obito;
/

