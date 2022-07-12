package apex.exemplopolimorfismo;

import javax.swing.JOptionPane;

public class ExemploPolimorfismo {
    
    public static void mostraTotal(funcionario fun){
        JOptionPane.showMessageDialog(null, "O Funcionario "+fun.Nome + "Vendeu um total de "+fun.totalVendas());
    }
    public static void main(String[] args) {
        carro carro1 = new carro();
        
        vendedor vendedorexemplo = new vendedor();
        gerente gerenteExemplo = new gerente();
        vendedorexemplo.vendasManha = 50;
        vendedorexemplo.vendasTarde = 30;
        vendedorexemplo.Nome = "Lucas";
        gerenteExemplo.vendedor1.vendasManha = 50;
        gerenteExemplo.vendedor1.vendasTarde = 30;
        gerenteExemplo.vendedor2.vendasManha = 70;
        gerenteExemplo.vendedor2.vendasTarde = 20; 
        gerenteExemplo.Nome = "Fransisco";
        // JOptionPane.showMessageDialog(null, gerenteExemplo.totalVendas());
       // JOptionPane.showMessageDialog(null, vendedorexemplo.totalVendas());
        mostraTotal(gerenteExemplo);
        mostraTotal(vendedorexemplo);
    }
}
