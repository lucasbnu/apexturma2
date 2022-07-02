package apex.operadorternario;

import javax.swing.JOptionPane;

public class OperadorTernario {

    public static void main(String[] args) {
        int idade = 0;
        String mensagem = "";
        
        idade = Integer.parseInt( JOptionPane.showInputDialog("Qual sua idade") );
        
        mensagem = idade >= 18 ? "Maior" : "Menor";
        JOptionPane.showMessageDialog(null, "Você é de "+mensagem);
        
    }
}
