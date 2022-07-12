package apex.testegeteset1;

import javax.swing.JOptionPane;

public class TesteGeteSet1 {

    public static void main(String[] args) {
        pessoa p1 = new pessoa();
        p1.setNome("Lucas de Sena");
        JOptionPane.showMessageDialog(null, p1.getNome());
        JOptionPane.showMessageDialog(null, p1.getNomeMaiusculo());
        JOptionPane.showMessageDialog(null, p1.getNomeMinusculo());        
    }
}
