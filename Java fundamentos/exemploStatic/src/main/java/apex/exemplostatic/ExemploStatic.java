package apex.exemplostatic;

import javax.swing.JOptionPane;


public class ExemploStatic {
    public static void mostramensagem(){
        String mensagem = JOptionPane.showInputDialog("Insita a mensagem");
        System.out.println(utilitarioNumerio.retornaEmMaiusculo(mensagem));
    }
    
    public static void main(String[] args) {
        utilitarioNumerio.numero1 = 20;
        utilitarioNumerio.numero2 = 20;
        int soma = utilitarioNumerio.SomaNumeros();
        mostramensagem();
        System.out.println(soma);
    }
}
