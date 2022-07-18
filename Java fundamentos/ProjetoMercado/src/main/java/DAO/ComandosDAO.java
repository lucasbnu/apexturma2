package DAO;

import DTO.Tabela;
import UTIL.Campo;
import java.util.ArrayList;

public class ComandosDAO {
    public String apagaRegistro(Tabela tb){
        String comando = "DELETE FROM "+tb.nomeTabela+" WHERE ";   
        String comandoWhere = "";
        ArrayList<Campo> listaCampos = tb.retornaCampos();
        for (Campo campo : listaCampos) {
            if (campo.chavePrimaria){
               comandoWhere = campo.nomeCampo + " = "+ campo.valorCampo.toString();
            }
        }
        return comando + comandoWhere;
    }
}
