package apex.aprendendoscanner;

import java.util.Scanner;

public class AprendendoScanner {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite seu nome:");
        String nome;
        nome = teclado.nextLine();
        System.out.println("O Nome digitado é: "+nome);
    }
}
