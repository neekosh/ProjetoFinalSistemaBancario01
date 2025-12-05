package com.unileste.projetofinal.entidades;
import com.unileste.projetofinal.utilitarios.*;
import javax.swing.JOptionPane;
public class ContaCorrente extends Conta {
    private double limiteChequeEspecial;

    public ContaCorrente(String numero, Cliente proprietario, double limiteChequeEspecial) {
        super(numero, proprietario);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }
    
    @Override
    public void depositar(double valor){
        if (valor <= 0){
            JOptionPane.showMessageDialog(null,"Valor de depósito inválido. Use um valor maior que zero." );
            throw new IllegalArgumentException("Valor de depósito inválido.");
        }
        saldo += valor;
        adicionarTransacao("Depósito de R$ " + String.format("%.2f", valor));
    }
    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0){
            JOptionPane.showMessageDialog(null,"Valor de saque inválido. Use um valor maior do que zero." );
            throw new IllegalArgumentException("Valor de saque inválido.");
        }

        double limiteDisponivel = saldo + limiteChequeEspecial;
        if (valor > limiteDisponivel){
            JOptionPane.showMessageDialog(null,"Valor de saque insuficiente. Limite disponível: R$ "+String.format("%.2f", limiteDisponivel));
            throw new SaldoInsuficienteException("Valor de saque insuficiente.");
        }

        saldo -= valor;
        adicionarTransacao("Saque de R$ " + String.format("%.2f", valor));
    }


    @Override
    public void transferir(Conta destino, double valor)
            throws SaldoInsuficienteException {

        if (destino == null){
            JOptionPane.showMessageDialog(null, "ERRO: Conta destino não encontrada");
            throw new IllegalArgumentException("Conta destino não encontrada.");
        }
        if (valor <= 0.0){
            JOptionPane.showMessageDialog(null, "Valor de transferência inválido. Use um valor maior que zero.");
            throw new IllegalArgumentException("Valor de transferência inválido.");
        }

        double limiteDisponivel = saldo + limiteChequeEspecial;
        if (valor > limiteDisponivel){
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para transferência. Limite disponível: R$ "+String.format("%.2f", limiteDisponivel));
            throw new SaldoInsuficienteException("Saldo insuficiente para transferência.");
        }

        saldo -= valor;
        
        destino.depositar(valor);

        adicionarTransacao("Transferência de R$ "+String.format("%.2f", valor)+" para conta "+destino.getNumero());

        destino.adicionarTransacao("Transferência recebida de R$ "+String.format("%.2f", valor)+" da conta " + this.getNumero());
    }
    
    @Override
    public String toString(){
        return super.toString()+", limite cheque especial="+String.format("%.2f", limiteChequeEspecial);
    }
}