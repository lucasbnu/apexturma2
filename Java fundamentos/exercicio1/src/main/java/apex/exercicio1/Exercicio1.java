

package apex.exercicio1;

import javax.swing.JOptionPane;


public class Exercicio1 {

    public static void main(String[] args) {
        String nomeMaisVelho = "", nome = "";
        int idadeMaisVelho = -1, idade = 0;
        for (int index = 1; index <= 4; index++) {
            nome = JOptionPane.showInputDialog("Qual o nome da pesso "+index+"º:");
            idade = Integer.parseInt(JOptionPane.showInputDialog("Qual a idade da pessoa "+index+"º:"));
            if (idade > idadeMaisVelho){
               idadeMaisVelho = idade;
               nomeMaisVelho = nome; 
            }
        }
        JOptionPane.showMessageDialog(null, "A pessoa mais velha é "+nomeMaisVelho);
    }
}
