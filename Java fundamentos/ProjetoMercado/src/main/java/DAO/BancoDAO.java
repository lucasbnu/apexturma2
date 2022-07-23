package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BancoDAO {
    public Connection conn;
    public PreparedStatement ps;
    public BancoDAO(){ 
         String textoConexao = "jdbc:mysql://localhost:3306/projetomercado?user=root&password=";
         try {
            conn = DriverManager.getConnection(textoConexao);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "BancoDAO.BancoDAO: "+e.getMessage());
        }
    }
    public boolean executaComando(String Comando){
        try {
            ps = conn.prepareStatement(Comando);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "BancoDAO.executaComando: "+ex.getMessage());
            return false;
        }
    }
}

