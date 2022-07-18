package apex.exercicioclasseabstrata;

import javax.swing.JOptionPane;

public class ExercicioClasseAbstrata {

    public static void main(String[] args) {
        cachorro cachorroExemplo = new cachorro();
        gato gatoExemplo = new gato();
        papagaio papagaioExemplo = new papagaio();
        JOptionPane.showMessageDialog(null, cachorroExemplo.emiteSom());
        JOptionPane.showMessageDialog(null, gatoExemplo.emiteSom());
        JOptionPane.showMessageDialog(null, papagaioExemplo.emiteSom());
    }
}
