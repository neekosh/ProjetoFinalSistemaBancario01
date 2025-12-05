package com.unileste.projetofinal.entidades;
import com.unileste.projetofinal.utilitarios.*;
import javax.swing.JOptionPane;
public class ContaPoupanca extends Conta {
    private double taxaRendMensal;
    
    public ContaPoupanca(String numero, Cliente proprietario, double taxaRendMensal){
        super(numero, proprietario);
        this.taxaRendMensal = taxaRendMensal;
    }
    
    @Override
    public void depositar(double valor){
        if (valor <= 0){
            JOptionPane.showMessageDialog(null,"Valor de depósito inválido. Use um valor maior do que zero." );
            throw new IllegalArgumentException("Valor de depósito inválido");
        }
        
        saldo += valor;
        adicionarTransacao("Depósito de R$ " +String.format("%.2f", valor));
    }
    @Override
    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor <= 0){
            JOptionPane.showMessageDialog(null,"Valor de saque inválido. Use um valor maior do que zero." );
            throw new IllegalArgumentException("Valor de saque inválido");
        }
        if (valor > saldo){
            JOptionPane.showMessageDialog(null, "Valor de saque inválido. Use um valor menor ou igual ao saldo disponível.");
            throw new SaldoInsuficienteException("Saldo insuficiente para o saque.");
        }
        
        saldo -= valor;
        adicionarTransacao("Saque de R$ " + String.format("%.2f", valor));
    }

   @Override
   public void transferir(Conta destino, double valor) throws SaldoInsuficienteException{
       if(valor <=0){
           JOptionPane.showMessageDialog(null, "Valor inválido para a transerência. Use um valor maior do que zero");
           throw new IllegalArgumentException("Valor inválido para a transferência.");
       }
       
       if(valor > saldo){
           JOptionPane.showMessageDialog(null, "Valor inválido para a transferência. Use um valor menor ou igual ao saldo disponível");
           throw new SaldoInsuficienteException("Saldo insuficiente para a transferência.");
       }else{
           // usa o método sacar da própria conta poupança
           this.sacar(valor);
           //usa o método depositar da conta destino
           destino.depositar(valor);
            adicionarTransacao("Transferência de R$ "+String.format("%.2f", valor)+" para conta "+destino.getNumero());
           destino.adicionarTransacao("Transferência recebida de R$ "+String.format("%.2f", valor)+" da conta "+this.getNumero());
       }
       
   }
    
    public void renderJuros(){
        double rendimento = saldo * taxaRendMensal;
        saldo += rendimento;
        adicionarTransacao("Rendimento mensal de R$ "+String.format("%.2f", rendimento));
    }
    
   @Override
    public String toString(){
        return super.toString()+", taxa de juros mensal="+taxaRendMensal;
    }
    
    public double getTaxaRendMensal() { //get para o DAO gravar a taxa
    return this.taxaRendMensal;
    }

}