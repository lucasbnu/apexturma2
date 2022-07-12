package apex.calculadorimc;

import javax.swing.JOptionPane;

public class CalculadorIMC {
    public static void mostraIMC( pessoa parametro){
        
      JOptionPane.showMessageDialog(null, parametro.nome + " seu IMC é "+parametro.pegaIMC());
    }
    
    
    
    
    
    

    public static void main(String[] args) {
        String nome = JOptionPane.showInputDialog("Insira seu nome:");
        pessoa pessoa1;
        pessoa1 = new pessoa(nome);
 
      
      pessoa1.altura = 1.80;
      pessoa1.peso = 100.0;
      mostraIMC(pessoa1);  
    }
}
