/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VIEW;

import DAO.ComandosDAO;
import DAO.ComboBoxDAO;
import DTO.ClientesDTO;
import DTO.FuncionariosDTO;
import DTO.ProdutosDTO;
import DTO.VendasDTO;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class VendasView extends javax.swing.JFrame {    
    private ComboBoxDAO ControladorCBClienteDAO;
    private ComboBoxDAO ControladorCBFuncionarioDAO;
    private ComboBoxDAO ControladorCBProduto;
    private int acao = 0; // 0 = vizualizando 1 = inserindo 2 = editando
    /**
     * Creates new form ClientesView
     */
    public VendasView() {
        ClientesDTO cli = new ClientesDTO();
        FuncionariosDTO fun = new FuncionariosDTO();
        ProdutosDTO pro = new ProdutosDTO();
        initComponents();
         ControladorCBClienteDAO = new ComboBoxDAO(edtfk_cliente,cli.getID_CLIENTE(),cli.getNome_cliente(),cli);    
         ControladorCBFuncionarioDAO = new ComboBoxDAO(edtfk_funcionario, fun.getId_funcionario(), fun.getNome_funcionario(), fun);
         ControladorCBProduto = new ComboBoxDAO(edtFk_produto,pro.getId_produto(),pro.getDescricao_produto(),pro);
         limpaCamposTela();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        edtIDvenda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btncancelar = new javax.swing.JButton();
        btnconfirmar = new javax.swing.JButton();
        btnnovo = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btnapagar = new javax.swing.JButton();
        cbCampos = new javax.swing.JComboBox<>();
        edtPesquisa = new javax.swing.JTextField();
        btnPesquisar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        cbOperacao = new javax.swing.JComboBox<>();
        edtQuantidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        edtfk_cliente = new javax.swing.JComboBox<>();
        edtfk_funcionario = new javax.swing.JComboBox<>();
        edtFk_produto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setText("Identificador venda");

        edtIDvenda.setEditable(false);

        jLabel2.setText("Cliente");

        jLabel3.setText("Funcionario");

        jLabel4.setText("Produto");

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabela);

        btncancelar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\uso\\cancel.png")); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.setEnabled(false);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnconfirmar.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        btnconfirmar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\uso\\confirm.png")); // NOI18N
        btnconfirmar.setText("CONFIRMAR");
        btnconfirmar.setEnabled(false);
        btnconfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnconfirmarActionPerformed(evt);
            }
        });

        btnnovo.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\24\\new.png")); // NOI18N
        btnnovo.setText("NOVO");
        btnnovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnovoActionPerformed(evt);
            }
        });

        btneditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\24\\update.png")); // NOI18N
        btneditar.setText("EDITAR");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnapagar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\24\\delete.png")); // NOI18N
        btnapagar.setText("APAGAR");
        btnapagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnapagarActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\24\\Search.png")); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnLimpar.setIcon(new javax.swing.ImageIcon("C:\\Users\\lucas\\Downloads\\icones\\24\\Clean.png")); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        cbOperacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Igual", "Contém" }));

        edtQuantidade.setEditable(false);

        jLabel5.setText("Quantidade");

        edtfk_cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "lucas", "joao", "" }));
        edtfk_cliente.setSelectedIndex(-1);
        edtfk_cliente.setEnabled(false);

        edtfk_funcionario.setEnabled(false);

        edtFk_produto.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnconfirmar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbOperacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnnovo)
                        .addGap(18, 18, 18)
                        .addComponent(btneditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnapagar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(110, 110, 110))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtIDvenda, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtfk_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edtfk_funcionario, 0, 210, Short.MAX_VALUE)
                            .addComponent(edtFk_produto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(114, 114, 114)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(edtfk_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(edtIDvenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(edtfk_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(edtFk_produto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbOperacao)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbCampos)
                    .addComponent(edtPesquisa))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnconfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnovo)
                    .addComponent(btneditar)
                    .addComponent(btnapagar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void atualizaRegistrosTela(){
        if (acao == 0){ 
            VendasDTO cli = new VendasDTO();
            ComandosDAO DAO = new ComandosDAO();
            tabela.setModel(DAO.retornaRegistroCRUD(cli,""));   
            cbCampos.removeAllItems();
            ControladorCBClienteDAO.atualizaDadosComboBox();
            ControladorCBFuncionarioDAO.atualizaDadosComboBox();
            ControladorCBProduto.atualizaDadosComboBox();
            Set<String> apelidos = cli.retornaApelidoCampos().keySet();
            for (String apelido : apelidos) {
                 cbCampos.addItem(apelido);
            }
        } 
    }
    
    private void limpaCamposTela(){
        edtIDvenda.setText("");
        edtQuantidade.setText("");
        edtFk_produto.setSelectedIndex(-1);
        edtfk_funcionario.setSelectedIndex(-1);
        edtfk_cliente.setSelectedIndex(-1);
    }
    
    private void modoEdicao(boolean editando){
        edtfk_funcionario.setEnabled(editando);
        edtfk_cliente.setEnabled(editando);
        edtFk_produto.setEnabled(editando);
        edtQuantidade.setEditable(editando);
        btncancelar.setEnabled(editando);
        btnconfirmar.setEnabled(editando);
        btnnovo.setEnabled(!editando);
        btneditar.setEnabled(!editando);
        btnapagar.setEnabled(!editando); 
        if (!editando){
            acao = 0;
        }
    }
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        atualizaRegistrosTela();
    }//GEN-LAST:event_formWindowActivated

    private void btnnovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnovoActionPerformed
        modoEdicao(true);
        acao = 1;
        limpaCamposTela();
    }//GEN-LAST:event_btnnovoActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        modoEdicao(false);
        limpaCamposTela();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnconfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnconfirmarActionPerformed
        VendasDTO cli = new VendasDTO();
        ComandosDAO comando = new ComandosDAO();

        cli.setFk_funcionario(ControladorCBFuncionarioDAO.retornaChaveSelecionada());
        cli.setFk_cliente(ControladorCBClienteDAO.retornaChaveSelecionada());
        cli.setFk_produto(ControladorCBProduto.retornaChaveSelecionada());
        cli.setQuantidade( Integer.parseInt( edtQuantidade.getText() ) );
        if (acao == 1){
           comando.insereRegistro(cli);
        }else{
           cli.setId_venda(Integer.parseInt(edtIDvenda.getText()));
           comando.atualizaRegistro(cli);
                }
        atualizaRegistrosTela();
        limpaCamposTela();
        modoEdicao(false);
    }//GEN-LAST:event_btnconfirmarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        modoEdicao(true);
        acao = 2;
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnapagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnapagarActionPerformed
        String mensagem = "";
        VendasDTO cli = new VendasDTO();
        ComandosDAO comando = new ComandosDAO();
        mensagem = "Você realmente deseja apagar a venda:\n"+
                   edtIDvenda.getText()+"\n"+
                   "Essa ação não pode ser desfeita!";
        
        if (JOptionPane.showConfirmDialog(this, mensagem,"Apagando Venda",0,2) == 0){
           cli.setId_venda(Integer.parseInt(edtIDvenda.getText()));
           comando.apagaRegistro(cli);
           atualizaRegistrosTela();
           limpaCamposTela();
        }
    }//GEN-LAST:event_btnapagarActionPerformed

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        if (acao == 0){
        edtIDvenda.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
        //edtfk_cliente.setText( tabela.getValueAt(tabela.getSelectedRow(),1).toString() );
        ControladorCBClienteDAO.posicionaComboBoxPelaChave(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(),1).toString()) );
        ControladorCBFuncionarioDAO.posicionaComboBoxPelaChave(Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(),3).toString()) );
        ControladorCBProduto.posicionaComboBoxPelaChave( Integer.parseInt(tabela.getValueAt(tabela.getSelectedRow(),2).toString()) );
        edtQuantidade.setText( tabela.getValueAt(tabela.getSelectedRow(),4).toString() );
        
        }
    }//GEN-LAST:event_tabelaMouseClicked

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        VendasDTO cli = new VendasDTO();
        ComandosDAO DAO = new ComandosDAO();
        String operacao = " = ";
        String contem = "";
        if (cbOperacao.getSelectedIndex() == 1){
           operacao = " like ";
           contem = "%";
        }
        String campo = cli.retornaApelidoCampos().get(cbCampos.getSelectedItem());
        String comando = "WHERE "+
        campo +operacao+"'"+contem+edtPesquisa.getText()+contem+"'";
        //JOptionPane.showMessageDialog(null, comando);
        tabela.setModel(DAO.retornaRegistroCRUD(cli,comando)); 
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        atualizaRegistrosTela();
        edtPesquisa.setText("");
    }//GEN-LAST:event_btnLimparActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendasView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendasView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnapagar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnconfirmar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnnovo;
    private javax.swing.JComboBox<String> cbCampos;
    private javax.swing.JComboBox<String> cbOperacao;
    private javax.swing.JComboBox<String> edtFk_produto;
    private javax.swing.JTextField edtIDvenda;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JTextField edtQuantidade;
    private javax.swing.JComboBox<String> edtfk_cliente;
    private javax.swing.JComboBox<String> edtfk_funcionario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables

}
