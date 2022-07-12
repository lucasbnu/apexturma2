package apex.calculadorimc;

public class pessoa {
    public String nome;
    public Double peso;
    public Double altura;
    public pessoa( String parametroNome){
        nome = parametroNome;
    }
    public Double pegaIMC(){
        return  peso / (altura * altura);
    }     
}
