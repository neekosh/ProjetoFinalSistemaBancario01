package com.unileste.projetofinal.gui;
import com.unileste.projetofinal.entidades.*;
import com.unileste.projetofinal.operacoes.Banco;
import javax.swing.JOptionPane;
public class ClientePanel extends javax.swing.JPanel {
    private Banco banco;
    public ClientePanel() {
        initComponents();
    }

      public ClientePanel(Banco banco) {
        this.banco = banco;
    }


      
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tituloCadClientes = new javax.swing.JLabel();

        tituloCadClientes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloCadClientes.setText("Cadastro de Clientes");
        tituloCadClientes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(200, Short.MAX_VALUE)
                .addComponent(tituloCadClientes)
                .addGap(194, 194, 194))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(tituloCadClientes)
                .addContainerGap(471, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel tituloCadClientes;
    // End of variables declaration//GEN-END:variables
}
