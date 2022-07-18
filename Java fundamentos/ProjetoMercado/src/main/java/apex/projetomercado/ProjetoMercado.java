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
        DTO.setDescricao_produto("Controle");
        DTO.setPreco(25.0f);
        DTO.setSituacao('A');
        String comando = DAO.insereRegistro(DTO);

        JOptionPane.showMessageDialog(null, comando);
    }
}
