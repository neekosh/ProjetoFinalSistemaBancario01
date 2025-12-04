package com.unileste.projetofinal.operacoes;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;
import com.unileste.projetofinal.utilitarios.ClienteNaoEncontradoException;
import com.unileste.projetofinal.utilitarios.ContaNaoEncontradaException;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Banco {
    private String nomeBanco;
    private Map<String,Cliente> clientes; //cliente por cpf <cpf,cliente>
    private Map<String, Conta> contas; //contas por numero <numero da conta,cliente>
    private int proximoNumeroConta;
    
    public Banco(String nomeBanco) {
        if (nomeBanco == null || nomeBanco.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do banco não pode ser vazio.");
            }
    
        this.nomeBanco = nomeBanco;
        this.clientes = new HashMap<>(); //vai "guardar" os dados do map
        this.contas = new HashMap<>();
        this.proximoNumeroConta = 1; //contador de contas
    }
    
    //metodo para cadastrar cliente no banco
    public void cadastrarCliente(Cliente cliente){
        if (cliente == null){
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        String cpf = cliente.getCpf();
        
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do cliente não pode ser vazio.");
        }
        if (clientes.containsKey(cpf)){ //verificar se o cpf é duplo
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }
        clientes.put(cpf, cliente);
    }
    
    //metodo para buscar cliente
    public Cliente buscarCliente(String cpf) throws ClienteNaoEncontradoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        Cliente cliente = clientes.get(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente com CPF "+cpf+" não encontrado.");
        }
        return cliente;
    }
    //metodo para buscar conta
    public Conta buscarConta(String numeroConta) throws ContaNaoEncontradaException {
        if (numeroConta == null || numeroConta.trim().isEmpty()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio.");
        }
        Conta conta = contas.get(numeroConta);
        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta "+numeroConta+" não encontrada.");
        }
        return conta;
    }
    //método para gerar um novo número de conta
    private String gerarNumeroConta(){
        String numero = String.valueOf(proximoNumeroConta);
        proximoNumeroConta++;
        return numero;
    }
    //método pra abrir a conta
    public Conta abrirConta(Cliente cliente,String tipoConta,double... parametros) //double... é tipo vários double opcionais e vira um array de doubles
            throws ClienteNaoEncontradoException {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        String cpf = cliente.getCpf();
        //garatir que o cliente existe no banco
        if (!clientes.containsKey(cpf)) {
            throw new ClienteNaoEncontradoException("Cliente com CPF "+cpf+" não está cadastrado no banco.");
        }

        if (tipoConta == null || tipoConta.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de conta não pode ser vazio.");
        }

        //gerar número de conta
        String numeroConta = gerarNumeroConta();
        Conta novaConta;
        
        if (tipoConta.equalsIgnoreCase("corrente")) {

            if (parametros.length == 0) {
                throw new IllegalArgumentException("É necessário informar o limite do cheque especial para conta corrente.");
            }

            double limiteChequeEspecial = parametros[0];
            novaConta = new ContaCorrente(numeroConta, cliente, limiteChequeEspecial);

        } else if (tipoConta.equalsIgnoreCase("poupanca") ||
                   tipoConta.equalsIgnoreCase("poupança")) {
            if (parametros.length == 0) {
                throw new IllegalArgumentException("É necessário informar a taxa de rendimento mensal para conta poupança.");
            }
            double taxaRendMensal = parametros[0];
            novaConta = new ContaPoupanca(numeroConta, cliente, taxaRendMensal);
        } else {
            throw new IllegalArgumentException("Tipo de conta inválido. Use 'corrente' ou 'poupanca'.");
        }
        //guardar a conta no mapa
        contas.put(numeroConta, novaConta);
        return novaConta;
    }
    
    public void realizarDeposito(String numeroConta, double valor)throws ContaNaoEncontradaException{
        Conta conta = buscarConta(numeroConta);
        conta.depositar(valor);
    }
    
    public void realizarSaque(String numeroConta, double valor)throws ContaNaoEncontradaException, SaldoInsuficienteException{
        Conta conta = buscarConta(numeroConta);
        conta.sacar(valor);
    }
    public void realizarTransferencias (String contaOrigem, String contaDestino, double valor)throws ContaNaoEncontradaException, SaldoInsuficienteException {
        Conta origem = buscarConta(contaOrigem);
        Conta destino = buscarConta(contaDestino);
        origem.transferir(destino, valor);
    }
    //getters
    public Map<String, Cliente> getClientes(){
        return new HashMap<>(clientes);
    }
    public Map<String, Conta> getContas(){
        return new HashMap<>(contas);
    }
    public String getNomeBanco(){
        return nomeBanco;
    }
}