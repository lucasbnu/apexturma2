package apex.projetomercado;

import DAO.ComandosDAO;
import DTO.ClientesDTO;
import DTO.produtosDTO;
import javax.swing.JOptionPane;



public class ProjetoMercado {

    public static void main(String[] args) {
        ComandosDAO DAO = new ComandosDAO();
        produtosDTO DTO = new produtosDTO();
        DTO.setId_produto(18);
        DTO.setDescricao_produto("Computador");
        DTO.setPreco(2000.0f);
        DTO.setSituacao('A');
        String comando = DAO.atualizaRegistro(DTO);

        JOptionPane.showMessageDialog(null, comando);
    }
}
