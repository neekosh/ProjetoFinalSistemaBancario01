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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(tituloConta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(tituloTaxaRendimentoMensal)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTaxaRendimentoMensal, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(tituloLimiteChequeEspecial)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtLimiteChequeEspecial))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tituloTipoConta)
                                        .addComponent(tituloCpf)
                                        .addComponent(tituloSaldo))
                                    .addGap(53, 53, 53)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTipoConta, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                        .addComponent(txtCpf)
                                        .addComponent(txtSaldo, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAbrirConta)
                                .addGap(33, 33, 33)
                                .addComponent(btnBuscarConta)
                                .addGap(26, 26, 26)
                                .addComponent(btnListarContas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(btnExcluirConta)))))
                .addGap(34, 34, 34))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tituloConta)
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloTipoConta)
                    .addComponent(txtTipoConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloCpf)
                    .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloSaldo)
                    .addComponent(txtSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloLimiteChequeEspecial)
                    .addComponent(txtLimiteChequeEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tituloTaxaRendimentoMensal)
                    .addComponent(txtTaxaRendimentoMensal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirConta)
                    .addComponent(btnBuscarConta)
                    .addComponent(btnListarContas)
                    .addComponent(btnExcluirConta))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtLimiteChequeEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLimiteChequeEspecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLimiteChequeEspecialActionPerformed

    private void btnAbrirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirContaActionPerformed
    
    }//GEN-LAST:event_btnAbrirContaActionPerformed

    private void btnBuscarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarContaActionPerformed
       

    }//GEN-LAST:event_btnBuscarContaActionPerformed

    private void btnListarContasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarContasActionPerformed
       

    }//GEN-LAST:event_btnListarContasActionPerformed

    private void btnExcluirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirContaActionPerformed
       
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
    private javax.swing.JLabel tituloConta;
    private javax.swing.JLabel tituloCpf;
    private javax.swing.JLabel tituloLimiteChequeEspecial;
    private javax.swing.JLabel tituloSaldo;
    private javax.swing.JLabel tituloTaxaRendimentoMensal;
    private javax.swing.JLabel tituloTipoConta;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtLimiteChequeEspecial;
    private javax.swing.JTextField txtSaldo;
    private javax.swing.JTextField txtTaxaRendimentoMensal;
    private javax.swing.JTextField txtTipoConta;
    // End of variables declaration//GEN-END:variables
}
