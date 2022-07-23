package apex.projetomercado;

import DAO.ComandosDAO;
import DTO.ClientesDTO;
import DTO.produtosDTO;
import javax.swing.JOptionPane;



public class ProjetoMercado {

    public static void main(String[] args) {
        ClientesDTO CliDTO = new ClientesDTO();
        ComandosDAO DAO = new ComandosDAO();
        CliDTO.setIdade(23);
        CliDTO.setNome_cliente("Lucas de Sena");
        CliDTO.setSituacao('A');
        String comando = DAO.insereRegistro(CliDTO);
        JOptionPane.showMessageDialog(null, comando);
    }
}
