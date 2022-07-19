package DAO;

import DTO.Tabela;
import UTIL.Campo;
import java.util.ArrayList;

public class ComandosDAO {

    public String atualizaRegistro(Tabela tb) {
        String comando = "UPDATE " + tb.nomeTabela + " SET ";
        String comandoWhere = " WHERE ";
        ArrayList<Campo> listaCampos = tb.retornaCampos();
        String ValoresTabela = "";
        int tamanhoLista = listaCampos.size();
        for (Campo Campo : listaCampos) {
            if (!Campo.chavePrimaria) {
                ValoresTabela += Campo.nomeCampo + " = ";
                if (Campo.valorCampo.getClass() == String.class || Campo.valorCampo.getClass() == Character.class) {
                    ValoresTabela += "'" + Campo.valorCampo + "'";
                } else {
                    ValoresTabela += Campo.valorCampo;
                }
                if (tamanhoLista != 1) {
                    ValoresTabela += ", ";
                }
            }
            tamanhoLista--;
        }

        for (Campo Icampo : listaCampos) {
            if (Icampo.chavePrimaria) {
                comandoWhere += Icampo.nomeCampo + " = " + Icampo.valorCampo.toString();
            }
        }

        return comando + ValoresTabela + comandoWhere;
    }

    public String apagaRegistro(Tabela tb) {
        String comando = "DELETE FROM " + tb.nomeTabela + " WHERE ";
        String comandoWhere = "";
        ArrayList<Campo> listaCampos = tb.retornaCampos();
        for (Campo Icampo : listaCampos) {
            if (Icampo.chavePrimaria) {
                comandoWhere = Icampo.nomeCampo + " = " + Icampo.valorCampo.toString();
            }
        }
        return comando + comandoWhere;
    }

    public String insereRegistro(Tabela tb) {
        ArrayList<Campo> listaCampos = tb.retornaCampos();
        String Comando = "INSERT INTO " + tb.nomeTabela;
        String CamposTabela = "(";
        int tamanhoLista = listaCampos.size();
        for (Campo ICampo : listaCampos) {
            if (!ICampo.chavePrimaria) {
                CamposTabela += ICampo.nomeCampo;
                if (tamanhoLista != 1) {
                    CamposTabela += ", ";
                }
            }
            tamanhoLista--;
        }
        CamposTabela += ")";

        String ValoresTabela = " VALUES (";
        tamanhoLista = listaCampos.size();
        for (Campo Campo : listaCampos) {
            if (!Campo.chavePrimaria) {
                if (Campo.valorCampo.getClass() == String.class || Campo.valorCampo.getClass() == Character.class) {
                    ValoresTabela += "'" + Campo.valorCampo + "'";
                } else {
                    ValoresTabela += Campo.valorCampo;
                }
                if (tamanhoLista != 1) {
                    ValoresTabela += ", ";
                }
            }
            tamanhoLista--;
        }
        ValoresTabela += ")";
        return Comando + CamposTabela + ValoresTabela;
    }
}
