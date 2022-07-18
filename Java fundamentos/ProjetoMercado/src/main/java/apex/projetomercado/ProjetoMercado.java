package apex.projetomercado;

import DAO.ComandosDAO;
import DTO.ClientesDTO;
import DTO.produtosDTO;
import javax.swing.JOptionPane;



public class ProjetoMercado {

    public static void main(String[] args) {
        ComandosDAO DAO = new ComandosDAO();
        produtosDTO DTO = new produtosDTO();
        DTO.setId_produto(16);
        String comando = DAO.apagaRegistro(DTO);
        JOptionPane.showMessageDialog(null, comando);
    }
}
