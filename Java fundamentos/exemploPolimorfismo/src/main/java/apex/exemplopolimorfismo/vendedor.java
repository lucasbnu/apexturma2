/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apex.exemplopolimorfismo;

/**
 *
 * @author lucas
 */
public class vendedor extends funcionario{
    public int vendasManha;
    public int vendasTarde;
    
    @Override
    public int totalVendas(){
     return vendasManha + vendasTarde;
    }
}
