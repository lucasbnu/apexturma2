package apex.exercicioarrays;

import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class ExercicioArrays {

    public static void main(String[] args) {
         int indiceMaiorIdade = 0;
         int indiceMenorIdade = 0;
         Integer[] Idades = new Integer[7];
         String Valores = "";
         for (int I = 0; I < 7; I++) {
            Idades[I] = Integer.parseInt(JOptionPane.showInputDialog("Por gentileza, informe sua idade."));
            if (Idades[I] > Idades[indiceMaiorIdade]){
               indiceMaiorIdade = I;
            }
            if (Idades[I] < Idades[indiceMenorIdade]){
               indiceMenorIdade = I;
            }            
        }
        for (int i = 0; i < Idades.length; i++) {
            Valores += Idades[i];
            if(i < Idades.length-1){
              Valores += ", ";
            }
        }
        
        
        
        
       
        JOptionPane.showMessageDialog(null, "A maior idade é " + Idades[indiceMaiorIdade] +" e a menor idade é "+Idades[indiceMenorIdade] + "\ndentre as idades "+Valores );
       
    }
}
