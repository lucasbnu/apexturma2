package apex.getsetjava;

import javax.swing.JOptionPane;


public class util {
  public static int retornaPerguntaInt(String pergunta){
      return Integer.parseInt(JOptionPane.showInputDialog(pergunta));
  }  
}
