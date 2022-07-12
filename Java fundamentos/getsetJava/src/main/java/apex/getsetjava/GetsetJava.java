package apex.getsetjava;

import javax.swing.JOptionPane;

public class GetsetJava {

    public static void main(String[] args) {
       pessoa p1 = new pessoa();
       p1.setNome(JOptionPane.showInputDialog("Qual seu nome?"));
       p1.setIdade(1);
       
    }
}
