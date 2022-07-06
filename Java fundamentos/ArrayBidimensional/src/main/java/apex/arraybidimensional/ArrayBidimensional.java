package apex.arraybidimensional;

import javax.swing.JOptionPane;

public class ArrayBidimensional {
    public static String retornaListaFormatada(String[][] lista){
        String texto = "";  
        
        for (int Linha = 0; Linha < lista.length; Linha++) {
            for (int Coluna = 0; Coluna < lista[0].length; Coluna++) {
                    texto += lista[Linha][Coluna];
                if (Coluna < lista[0].length -1){
                   texto += " | ";
                }
            }
            texto += "\n";
            
        }
      return texto;
    }
    public static String pegaPergunta(int valor){
         if (valor == 0){
             return "Insira o seu nome:";
         }else{
             return "Insira o sua idade:";
         }
    }
    public static void main(String[] args) {
         String[][] lista = new String[4][2];
         lista[0][0] = "Nome";
         lista[0][1] = "Idade";
         for (int Linha = 1; Linha < lista.length; Linha++) {
             for (int Coluna = 0; Coluna < lista[0].length; Coluna++){
                 
                 lista[Linha][Coluna] = JOptionPane.showInputDialog(pegaPergunta(Coluna));
                 
             }
        }
        
        JOptionPane.showMessageDialog(null, retornaListaFormatada(lista));
    }
    
}
