package com.unileste.projetofinal.gui;
import com.unileste.projetofinal.operacoes.Banco;
import com.unileste.projetofinal.entidades.Cliente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ClientePanel extends javax.swing.JPanel {
    private Banco banco;
    
    //Construtor para inicializar o banco
    public ClientePanel(Banco banco) {
        initComponents();
        this.banco = banco;
    }
    
    //Método para carregar a tabela 
    private void carregarTabela() {
    DefaultTableModel model = (DefaultTableModel) tabelaCadastroClientes.getModel(); //modelo da tabela
    model.setRowCount(0); // limpa as linhas existentes, garantindo que a tabela (ao ser carregada novamente), não mostre valores antigos

    // obtém a lista de clientes do Banco (em memória e sincronizada com o BD)
    List<Cliente> lista = banco.listarClientes();
    for (Cliente c : lista) {
        model.addRow(new Object[]{
            c.getNome(),
            c.getCpf(),
            c.getEndereco()
        });
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloCadClientes = new javax.swing.JLabel();
        tituloNome = new javax.swing.JLabel();
        tituloCpf = new javax.swing.JLabel();
        tituloEndereco = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCadastroClientes = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        btnListarClientes = new javax.swing.JButton();
        btnExcluirCliente = new javax.swing.JButton();

        tituloCadClientes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloCadClientes.setText("Cadastro de Clientes");
        tituloCadClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tituloNome.setText("Nome:");

        tituloCpf.setText("CPF:");

        tituloEndereco.setText("Endereço:");

        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 800));

        tabelaCadastroClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Endereço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCadastroClientes.setPreferredSize(new java.awt.Dimension(500, 800));
        jScrollPane1.setViewportView(tabelaCadastroClientes);

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnBuscarCliente.setText("Buscar cliente");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnListarClientes.setText("Listar cliente");
        btnListarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarClientesActionPerformed(evt);
            }
        });

        btnExcluirCliente.setText("Excluir Cliente");
        btnExcluirCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(tituloCadClientes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tituloEndereco)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloNome)
                            .addComponent(tituloCpf))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome)
                            .addComponent(txtCpf)
                            .addComponent(txtEndereco))
                        .addGap(175, 175, 175))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnListarClientes)
                        .addGap(29, 29, 29)
                        .addComponent(btnBuscarCliente)
                        .addGap(30, 30, 30)
                        .addComponent(btnExcluirCliente)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(tituloCadClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tituloNome))
                        .addGap(18, 18, 18)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tituloCpf))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloEndereco))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnBuscarCliente)
                    .addComponent(btnListarClientes)
                    .addComponent(btnExcluirCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
       try {
        Cliente c = new Cliente(txtNome.getText(),txtCpf.getText(),txtEndereco.getText());
        banco.cadastrarCliente(c);   //cadastra o cliente no Banco

        JOptionPane.showMessageDialog(this, "Cliente cadastrado!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }   
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
    try {
        String cpf = txtCpf.getText();
        Cliente c = banco.buscarCliente(cpf); //chama o método buscar cliente da classe Banco
        JOptionPane.showMessageDialog(this, "Cliente com o CPF: " +cpf+ " encontrado.");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }   
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnListarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarClientesActionPerformed
       carregarTabela(); //apenas chama o método carregar tabela, que já lista os clientes cadastrados
    }//GEN-LAST:event_btnListarClientesActionPerformed

    private void btnExcluirClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirClienteActionPerformed
        int linha = tabelaCadastroClientes.getSelectedRow(); // variável para pegar a linha selecionada pelo usuário

        //Se o usuário escolher uma linha inexitente
    if (linha == -1) {
        JOptionPane.showMessageDialog(this, "Selecione um cliente na tabela!");
        return;
    }
       //Pega os valores da linha escolhida pelo usuário 
    String cpf = tabelaCadastroClientes.getValueAt(linha, 1).toString(); //linha, 1 = índice do cpf na tabela (coluna 0 = nome, coluna 1 = cpf, coluna 2 = endereço)

    //Aparece uma janela pedindo a confirmação de exclusão de usuário
    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Deseja excluir o cliente com CPF " + cpf + "?",
            "Confirmar Exclusão",
            JOptionPane.YES_NO_OPTION
    );
    //Se o botão clicado for "Não", ele não exclui o usuário e o programa para aqui mesmo.
    if (confirm != JOptionPane.YES_OPTION) return;
    //Caso o botão clicado seja "Sim", tentamos executar a exclusão.
    //Colocamos tudo isso dentro de um try-catch pois o processo de exclusão pode dar erro
    try {
        banco.excluirCliente(cpf);  //chama o método do banco excluirCliente (excluimos o cliente pelo cpf pois o cpf é a chave de cliente - cpf único)

        JOptionPane.showMessageDialog(this, "Cliente removido!"); //exibe a mensagem
        carregarTabela(); // atualiza a tabela depois
        //Case dê algum erro:
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
    }
    }//GEN-LAST:event_btnExcluirClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluirCliente;
    private javax.swing.JButton btnListarClientes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCadastroClientes;
    private javax.swing.JLabel tituloCadClientes;
    private javax.swing.JLabel tituloCpf;
    private javax.swing.JLabel tituloEndereco;
    private javax.swing.JLabel tituloNome;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
