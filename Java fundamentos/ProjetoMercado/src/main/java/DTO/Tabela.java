package DTO;

import UTIL.Campo;
import java.util.ArrayList;

public abstract class Tabela {
    public String nomeTabela;
    public abstract ArrayList<Campo> retornaCampos();
}
