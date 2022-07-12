package apex.testegeteset1;


public class pessoa {
   private String nome;
   private int idade;
   private char sexo;

    public String getNome() {
        return nome;
    }
    
    public String getNomeMaiusculo() {
        return nome.toUpperCase();
    }

    public String getNomeMinusculo() {
        return nome.toLowerCase();
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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
   
}
