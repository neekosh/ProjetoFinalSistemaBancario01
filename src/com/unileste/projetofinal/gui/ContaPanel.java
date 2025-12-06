package com.unileste.projetofinal.gui;
import javax.swing.JOptionPane;
import com.unileste.projetofinal.entidades.*;
import javax.swing.table.DefaultTableModel;
import com.unileste.projetofinal.operacoes.Banco;
public class ContaPanel extends javax.swing.JPanel {
    private Banco banco;
    
    public ContaPanel(Banco banco) {
        initComponents();
        this.banco = banco;
    }

    private void carregarTabela() {
    DefaultTableModel modelo = (DefaultTableModel) tabelaContas.getModel();
    modelo.setRowCount(0); // limpa tabela

    for (Conta c : banco.listarContas()) {

     //instanceof -> um operador do Java que serve para verificar se um objeto é uma instância de uma classe específica
     //Operador ternário -> jeito mais curto de escrever if- else. (condição) ? valor_se_verdadeiro :  valor_se_falso
    String tipo = c instanceof ContaCorrente ? "CORRENTE" : "POUPANCA"; // A variável c é do tipo ContaCorrente?, se sim o valor da váriável é Corrente, se não é Poupança
    String cpf = c.getProprietario().getCpf();

    Double limite = c instanceof ContaCorrente ? ((ContaCorrente)c).getLimiteChequeEspecial(): null; //c é do tipo ContaCorrente? se sim, c pega o valor de limiteChequeEspecial, se não, mantém o valor como null

    Double taxa = c instanceof ContaPoupanca ? ((ContaPoupanca)c).getTaxaRendMensal(): null; //taxa é do tipo ContaPoupanca? se sim, c pega o valor de taxaRendaMensal, se não, mantém o valor como null

    //cria uma lista (array) contendo valores de tipos diferentes
    //cada elemento dentro do {} vira uma coluna
    modelo.addRow(new Object[]{
        c.getNumero(), //coluna 1
        tipo, //coluna 2
        cpf, //coluna 3
        limite, //coluna 4
        taxa, //coluna 5
        c.getSaldo()  //coluna 6
    });
}

}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        tituloConta = new javax.swing.JLabel();
        tituloTipoConta = new javax.swing.JLabel();
        tituloCpf = new javax.swing.JLabel();
        tituloSaldo = new javax.swing.JLabel();
        tituloLimiteChequeEspecial = new javax.swing.JLabel();
        tituloTaxaRendimentoMensal = new javax.swing.JLabel();
        txtTipoConta = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtSaldo = new javax.swing.JTextField();
        txtLimiteChequeEspecial = new javax.swing.JTextField();
        txtTaxaRendimentoMensal = new javax.swing.JTextField();
        btnAbrirConta = new javax.swing.JButton();
        btnBuscarConta = new javax.swing.JButton();
        btnListarContas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaContas = new javax.swing.JTable();
        btnExcluirConta = new javax.swing.JButton();
        tituloBuscarContaPeloNumero = new javax.swing.JLabel();
        tituloNumeroConta = new javax.swing.JLabel();
        txtNumeroBusca = new javax.swing.JTextField();

        jPanel1.setPreferredSize(new java.awt.Dimension(500, 800));

        tituloConta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloConta.setText("Conta");
        tituloConta.setFocusable(false);
        tituloConta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tituloTipoConta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tituloTipoConta.setText("Tipo de Conta:");

        tituloCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tituloCpf.setText("CPF:");

        tituloSaldo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tituloSaldo.setText("Saldo R$:");

        tituloLimiteChequeEspecial.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tituloLimiteChequeEspecial.setText("Limite Cheque Especial:");

        tituloTaxaRendimentoMensal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tituloTaxaRendimentoMensal.setText("Taxa Rendimento Mensal:");

        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        txtSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSaldoActionPerformed(evt);
            }
        });

        txtLimiteChequeEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLimiteChequeEspecialActionPerformed(evt);
            }
        });

        btnAbrirConta.setText("Abrir Conta");
        btnAbrirConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirContaActionPerformed(evt);
            }
        });

        btnBuscarConta.setText("Buscar Conta");
        btnBuscarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarContaActionPerformed(evt);
            }
        });

        btnListarContas.setText("Listar Contas");
        btnListarContas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarContasActionPerformed(evt);
            }
        });

        tabelaContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Número Conta", "Tipo", "CPF - Proprietário", "Limite Cheque Especial", "Taxa Rendimento Mensal"
            }
        ));
        jScrollPane1.setViewportView(tabelaContas);

        btnExcluirConta.setText("Excluir Conta");
        btnExcluirConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirContaActionPerformed(evt);
            }
        });

        tituloBuscarContaPeloNumero.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tituloBuscarContaPeloNumero.setText("Buscar conta pelo número");

        tituloNumeroConta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tituloNumeroConta.setText("Número da Conta:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(tituloConta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(tituloNumeroConta)
                                .addGap(18, 18, 18)
                                .addComponent(txtNumeroBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnBuscarConta))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(227, 227, 227)
                                .addComponent(tituloBuscarContaPeloNumero))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tituloTipoConta)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(btnAbrirConta)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(156, 156, 156)
                                        .addComponent(txtLimiteChequeEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnListarContas)
                                        .addGap(94, 94, 94)
                                        .addComponent(btnExcluirConta))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(tituloTaxaRendimentoMensal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTaxaRendimentoMensal, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tituloSaldo)
                        .addGap(235, 235, 235)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tituloCpf)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tituloLimiteChequeEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addGap(237, 237, 237))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloConta)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloTipoConta)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tituloCpf)
                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloSaldo)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tituloLimiteChequeEspecial)
                    .addComponent(txtLimiteChequeEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloTaxaRendimentoMensal)
                    .addComponent(txtTaxaRendimentoMensal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAbrirConta)
                            .addComponent(btnExcluirConta))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnListarContas)
                        .addGap(18, 18, 18)))
                .addComponent(tituloBuscarContaPeloNumero)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloNumeroConta)
                    .addComponent(txtNumeroBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarConta))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtLimiteChequeEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLimiteChequeEspecialActionPerformed

    }//GEN-LAST:event_txtLimiteChequeEspecialActionPerformed

    private void btnAbrirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirContaActionPerformed
        try {
            //lê o cpf e tipo digitados pelo usuário
        String cpf = txtCpf.getText().trim(); //.trim() -> remove os espaços em branco da string
        String tipo = txtTipoConta.getText().trim();

        // Busca cliente no banco, chamando o método da classe Banco
        Cliente cliente = banco.buscarCliente(cpf);

        //declara a variável da nova conta
        Conta novaConta;

        if (tipo.equalsIgnoreCase("corrente")) { //compara duas strings para verificar se são iguais, ignorando completamente a diferença entre letras maiúsculas e minúsculas.

            double limite = Double.parseDouble(txtLimiteChequeEspecial.getText());
            novaConta = banco.abrirConta(cliente, "corrente", limite);

        } else if (tipo.equalsIgnoreCase("poupanca") || tipo.equalsIgnoreCase("poupança")) {

            double taxa = Double.parseDouble(txtTaxaRendimentoMensal.getText());
            novaConta = banco.abrirConta(cliente, "poupanca", taxa);

        } else {
            JOptionPane.showMessageDialog(this, "Tipo inválido. Use 'corrente' ou 'poupanca'.");
            return;
        }

        JOptionPane.showMessageDialog(this,
                "Conta criada com sucesso!\nNúmero gerado: " + novaConta.getNumero());

  
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAbrirContaActionPerformed

    private void btnBuscarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarContaActionPerformed
     try {
        String numero = txtNumeroBusca.getText().trim();

        if (numero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o número da conta para buscar.");
            return;
        }

        Conta conta = banco.buscarConta(numero);

        String tipo, limite = "-", taxa = "-";

        if (conta instanceof ContaCorrente cc) {
            tipo = "CORRENTE";
            limite = String.valueOf(cc.getLimiteChequeEspecial());

        } else {
            ContaPoupanca cp = (ContaPoupanca) conta;
            tipo = "POUPANCA";
            taxa = String.valueOf(cp.getTaxaRendMensal());
        }

        String mensagem =
                "Número da Conta: " + conta.getNumero() + "\n" +
                "Tipo: " + tipo + "\n" +
                "CPF do Proprietário: " + conta.getProprietario().getCpf() + "\n" +
                "Saldo: " + conta.getSaldo() + "\n" +
                "Limite Cheque Especial: " + limite + "\n" +
                "Taxa Rendimento Mensal: " + taxa;

        JOptionPane.showMessageDialog(this, mensagem, "Dados da Conta", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao buscar conta:\n" + e.getMessage());
    }
    }//GEN-LAST:event_btnBuscarContaActionPerformed

    private void btnListarContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarContasActionPerformed
        carregarTabela();

    }//GEN-LAST:event_btnListarContasActionPerformed

    private void btnExcluirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirContaActionPerformed
    int linha = tabelaContas.getSelectedRow();

    if (linha == -1) {
        JOptionPane.showMessageDialog(this, "Selecione uma conta na tabela para excluir.");
        return;
    }

    // pega o número da conta da tabela (COLUNA 0)
    String numero = tabelaContas.getValueAt(linha, 0).toString();

    int confirm = JOptionPane.showConfirmDialog(
            this,
            "Deseja realmente excluir a conta " + numero + "?",
            "Excluir Conta",
            JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) return;

    try {
        banco.excluirConta(numero);
        JOptionPane.showMessageDialog(this, "Conta removida com sucesso!");
        carregarTabela(); // atualiza tabela

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage());
    }
    }//GEN-LAST:event_btnExcluirContaActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSaldoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirConta;
    private javax.swing.JButton btnBuscarConta;
    private javax.swing.JButton btnExcluirConta;
    private javax.swing.JButton btnListarContas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaContas;
    private javax.swing.JLabel tituloBuscarContaPeloNumero;
    private javax.swing.JLabel tituloConta;
    private javax.swing.JLabel tituloCpf;
    private javax.swing.JLabel tituloLimiteChequeEspecial;
    private javax.swing.JLabel tituloNumeroConta;
    private javax.swing.JLabel tituloSaldo;
    private javax.swing.JLabel tituloTaxaRendimentoMensal;
    private javax.swing.JLabel tituloTipoConta;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtLimiteChequeEspecial;
    private javax.swing.JTextField txtNumeroBusca;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtTaxaRendimentoMensal;
    private javax.swing.JTextField txtTipoConta;
    // End of variables declaration//GEN-END:variables
}
