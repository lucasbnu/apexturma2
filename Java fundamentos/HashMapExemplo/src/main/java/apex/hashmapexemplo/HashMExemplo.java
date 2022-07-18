package apex.hashmapexemplo;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class HashMExemplo {

    public static void main(String[] args) {
        HashMap<String,String> HM = new HashMap<>();
        HM.put("Michael", "Aluno");
        HM.put("Eduardo", "Pessoa Aleatória");
        HM.put("Wiliam", "Amigo Michael");
        String textoFormatado = "";
        for (Map.Entry<String,String> e : HM.entrySet()){
            textoFormatado += e.getKey() + " = "+e.getValue()+"\n";
        }
        utilApex.mostraMensagem(textoFormatado);
    }
}
