package com.unileste.projetofinal.entidades;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public abstract class Conta {
    private String numero;
    protected double saldo;
    private Cliente proprietario;
    private List<String> historicoTransacoes;

    //antes estava validando o atributo, agr é o parâmetro
    public Conta(String numero, Cliente proprietario) {
        //valida o numero
        if (numero == null || numero.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Número não pode ser nulo ou vazio. Digite um número válido.");
            throw new IllegalArgumentException("Número da conta inválido.");
        }
        //valida o proprietario
        if (proprietario == null) {
            JOptionPane.showMessageDialog(null,"Proprietário não pode ser nulo.");
            throw new IllegalArgumentException("Proprietário inválido.");
        }
        
        this.numero = numero;
        this.proprietario = proprietario;
        this.saldo = 0.0;
        this.historicoTransacoes = new ArrayList<String>();

        //registra a conta na lista do cliente
        proprietario.adicionarConta(this);
    }

    public String getNumero() {
        return numero;
    }
    public double getSaldo() {
        return saldo;
    }
    public Cliente getProprietario() {
        return proprietario;
    }
    public List<String> getHistoricoTransacoes() {
        return historicoTransacoes;
    }
    //adicionei uma validaçao aqui tbm
    protected void adicionarTransacao(String descricao){
        if (descricao != null && !descricao.trim().isEmpty()) {
            historicoTransacoes.add(descricao);
        } else {
            JOptionPane.showMessageDialog(null,"Descrição de transação vazia ou nula.");
        }
    }
    
    public abstract void depositar(double valor);
    public abstract void sacar(double valor) throws SaldoInsuficienteException;
    public abstract void transferir(Conta destino, double valor) throws SaldoInsuficienteException;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Conta)) return false;
        Conta outra = (Conta) o;
        return numero.equals(outra.numero);
    }
    @Override
    public int hashCode(){
        return numero.hashCode();
    }
    @Override
    public String toString(){
        return "Conta{" +
                "numero='" +numero+ '\'' +
                ",saldo" + String.format("%.2f", saldo) +
                ",proprietario=" + (proprietario != null ? proprietario.getNome() : "Sem Cliente") +
                '}';
    }
    
    public void setSaldo(double saldo) { //usado pelo DAO só para carregar o valor do banco
    this.saldo = saldo;
    }
}