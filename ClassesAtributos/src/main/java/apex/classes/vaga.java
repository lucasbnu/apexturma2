package apex.classes;

import javax.swing.JOptionPane;

public class vaga {
    public String nome;
    public int idade ;
    
    public void EhDeMaior(){
        if (idade >= 18){
           JOptionPane.showMessageDialog(null, "Você é de maior");
        }else{
            JOptionPane.showMessageDialog(null, "Você é de menor");
        }
    }
    
}
