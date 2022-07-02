package apex.conversoes;

import javax.swing.JOptionPane;

public class Conversoes {

    public static void main(String[] args) {
        int idade = 0;
        Double salario = 0.0;
        idade = Integer.parseInt( JOptionPane.showInputDialog("Qual sua Idade?") );
        salario = Double.parseDouble( JOptionPane.showInputDialog("Qual o seu salario?"));
        
        JOptionPane.showMessageDialog(null, "Sua idade é "+idade+" Seu salario é "+salario);
        
    }
}
