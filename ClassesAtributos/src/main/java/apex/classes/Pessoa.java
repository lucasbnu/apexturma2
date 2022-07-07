
package apex.classes;

import javax.swing.JOptionPane;

public class Pessoa{
    private String nome;
    public int idade;
    
    public Boolean EhDeMaior(){
         return idade >= 18;   
    } 
    public void defineNome(String nomeaDefinir){
        if (nomeaDefinir == "diego"){
            JOptionPane.showMessageDialog(null, "Esse nome não é aceito");
        }else{
           nome = nomeaDefinir;
        }
    }
    
    public String imprimePessoa(){
        return "O Nome é "+nome + " e a idade é " + idade;
    }
}
