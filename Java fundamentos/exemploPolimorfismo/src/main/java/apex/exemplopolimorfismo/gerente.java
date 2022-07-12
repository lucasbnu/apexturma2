/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apex.exemplopolimorfismo;

/**
 *
 * @author lucas
 */
public class gerente extends funcionario{
    vendedor vendedor1;
    vendedor vendedor2;
    public gerente(){
        vendedor1 = new vendedor();
        vendedor2 = new vendedor();        
    }
    
    @Override
    public int totalVendas(){
       return vendedor1.totalVendas() + vendedor2.totalVendas();
    }
}
