package apex.testegeteset2;

import javax.swing.JOptionPane;

public class pessoa {
   private String nome;
   private int idade;
   private String Email;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        if (Email.indexOf("@") != -1 && Email.indexOf(".") != -1 ){
            this.Email = Email;
        }else{
            JOptionPane.showMessageDialog(null, "Email: " + Email +" é invalido!");
        }
    }
   
}
