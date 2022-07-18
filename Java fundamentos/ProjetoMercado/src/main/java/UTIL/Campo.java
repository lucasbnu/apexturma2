package UTIL;

public class Campo<E> {
    public String nomeCampo;
    public E valorCampo;
    public boolean chavePrimaria = false;
    public Campo(){}
    
    public Campo(String nomeCampo){
        this.nomeCampo = nomeCampo;
    }
    
    public Campo(String nomeCampo, boolean chavePrimaria){
        this.nomeCampo = nomeCampo;
        this.chavePrimaria = chavePrimaria;
    }
}
