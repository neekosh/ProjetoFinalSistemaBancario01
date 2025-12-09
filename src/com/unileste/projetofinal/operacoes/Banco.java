package com.unileste.projetofinal.operacoes;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;
import com.unileste.projetofinal.utilitarios.ClienteNaoEncontradoException;
import com.unileste.projetofinal.utilitarios.ContaNaoEncontradaException;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;
import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ClienteDAOJdbc;
import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.dao.ContaDAOJdbc;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Banco {
    private String nomeBanco;
    private Map<String,Cliente> clientes; //cliente por cpf <cpf,cliente>
    private Map<String, Conta> contas; //contas por numero <numero da conta,cliente>
    private int proximoNumeroConta;
    
    //atributos para DAO
    private ClienteDAO clienteDAO;
    private ContaDAO contaDAO;
    
    public Banco(String nomeBanco) {
        if (nomeBanco == null || nomeBanco.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do banco não pode ser vazio.");
        }

        this.nomeBanco = nomeBanco;
        this.clientes = new HashMap<>();
        this.contas = new HashMap<>();
        this.proximoNumeroConta = 1;

        //inicializa os DAOs com implementação JDBC
        this.clienteDAO = new ClienteDAOJdbc();
        this.contaDAO = new ContaDAOJdbc();
        
        carregarDadosIniciais(); //carregar do banco pra memória. Carrega o banco já sabendo os dados lá
    }
    
    private void carregarDadosIniciais() {
    //carrega todos os clientes
    for (Cliente c : clienteDAO.listarTodos()) {
        clientes.put(c.getCpf(), c);
    }
    int maiorNumero = 0;
    //carrega todas as contas
    for (Conta conta : contaDAO.listarTodas()) {
        contas.put(conta.getNumero(), conta);
        try {
            int n = Integer.parseInt(conta.getNumero());
            if (n > maiorNumero) {
                maiorNumero = n;
            }
        } catch (NumberFormatException e) {
        }
    }
    this.proximoNumeroConta = maiorNumero + 1;
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

        //verifica no banco se já existe
        Cliente existente = clienteDAO.buscarPorCpf(cpf);
        if (existente != null) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
        }

        //grava no banco
        clienteDAO.inserir(cliente);

        clientes.put(cpf, cliente);
    }
    
    //metodo para buscar cliente
    public Cliente buscarCliente(String cpf) throws ClienteNaoEncontradoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        //tenta no map
        Cliente cliente = clientes.get(cpf);
        if (cliente != null) {
            return cliente;
        }
        //se não achou no map, busca no banco
        cliente = clienteDAO.buscarPorCpf(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
        }
        //guarda no mapa pra futuras buscas ficarem mais rápidas
        clientes.put(cpf, cliente);
        return cliente;
    }
    
    //metodo para buscar conta
    public Conta buscarConta(String numeroConta) throws ContaNaoEncontradaException {
        if (numeroConta == null || numeroConta.trim().isEmpty()) {
            throw new IllegalArgumentException("Número da conta não pode ser nulo ou vazio.");
        }

        //tenta no map
        Conta conta = contas.get(numeroConta);
        if (conta != null) {
            return conta;
        }
        //se não tiver no map, busca no banco
        conta = contaDAO.buscarPorNumero(numeroConta);
        if (conta == null) {
            throw new ContaNaoEncontradaException("Conta " + numeroConta + " não encontrada.");
        }
        //guarda no mapa
        contas.put(numeroConta, conta);
        return conta;
    }
    
    //método para gerar um novo número de conta
    private String gerarNumeroConta(){
        String numero = String.valueOf(proximoNumeroConta);
        proximoNumeroConta++;
        return numero;
    }
    //método pra abrir a conta
    public Conta abrirConta(Cliente cliente, String tipoConta, double... parametros)
            throws ClienteNaoEncontradoException {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        String cpf = cliente.getCpf();
        //garantir que o cliente existe no banco (usa buscarCliente, que já fala com o DAO)
        Cliente clienteBanco = buscarCliente(cpf);

        if (tipoConta == null || tipoConta.trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo de conta não pode ser vazio.");
        }

        String numeroConta = gerarNumeroConta();
        Conta novaConta;

        if (tipoConta.equalsIgnoreCase("corrente")) {
            if (parametros.length == 0) {
                throw new IllegalArgumentException("É necessário informar o limite do cheque especial para conta corrente.");
            }
            double limiteChequeEspecial = parametros[0];
            novaConta = new ContaCorrente(numeroConta, clienteBanco, limiteChequeEspecial);

        } else if (tipoConta.equalsIgnoreCase("poupanca") ||
                   tipoConta.equalsIgnoreCase("poupança")) {
            if (parametros.length == 0) {
                throw new IllegalArgumentException("É necessário informar a taxa de rendimento mensal para conta poupança.");
            }
            double taxaRendMensal = parametros[0];
            novaConta = new ContaPoupanca(numeroConta, clienteBanco, taxaRendMensal);

        } else {
            throw new IllegalArgumentException("Tipo de conta inválido. Use 'corrente' ou 'poupanca'.");
        }
        contas.put(numeroConta, novaConta);
        //salvar no banco via DAO
        contaDAO.inserir(novaConta);

        return novaConta;
    }
    
    public void realizarDeposito(String numeroConta, double valor)
            throws ContaNaoEncontradaException {
        Conta conta = buscarConta(numeroConta); //fala com DAO se precisar
        conta.depositar(valor);                //regra da conta (valida valor etc.)
        //após alterar o saldo em memória, gravar no banco
        contaDAO.atualizar(conta);
        contas.put(numeroConta, conta); //Atualizando o mapa
    }
    
    public void realizarSaque(String numeroConta, double valor)
            throws ContaNaoEncontradaException, SaldoInsuficienteException {
        Conta conta = buscarConta(numeroConta);
        conta.sacar(valor);
        contaDAO.atualizar(conta);
        contas.put(numeroConta, conta); //Atualizando o mapa
    }
    public void realizarTransferencias(String contaOrigem, String contaDestino, double valor)
            throws ContaNaoEncontradaException, SaldoInsuficienteException {
        Conta origem = buscarConta(contaOrigem);
        Conta destino = buscarConta(contaDestino);
        origem.transferir(destino, valor);
        // atualizar as duas contas no banco
        contaDAO.atualizar(origem);
        contaDAO.atualizar(destino);
    }
    
    
    //Criei um método adicional de Excluir cliente. Por mais que esse método exista no ClienteDAOJbdc(método excluir) precisei colocar ele aqui pois não posso chamar diretamente métodos das classes.DAO
    public void excluirCliente(String cpf) throws ClienteNaoEncontradoException {
    if (cpf == null || cpf.trim().isEmpty()) {
        throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
    }
    Cliente cliente = clientes.get(cpf);
    if (cliente == null) {
        cliente = clienteDAO.buscarPorCpf(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente com CPF " + cpf + " não encontrado.");
        }
    }
    java.util.List<Conta> contasDoCliente = new java.util.ArrayList<>();
    for (Conta c : contas.values()) {
        if (c.getProprietario().getCpf().equals(cpf)) {
            contasDoCliente.add(c);
        }
    }

    for (Conta c : contasDoCliente) {
        contas.remove(c.getNumero()); 
        contaDAO.remover(c.getNumero()); 
    }

    clienteDAO.remover(cpf);
    clientes.remove(cpf);
    }
    
    public void excluirConta(String numeroConta) throws ContaNaoEncontradaException {
    if (numeroConta == null || numeroConta.trim().isEmpty()) {
        throw new IllegalArgumentException("Número da conta não pode ser vazio.");
    }
    Conta conta = contas.get(numeroConta);
    if (conta == null) {
        throw new ContaNaoEncontradaException("Conta " + numeroConta + " não encontrada.");
    }
    contaDAO.remover(numeroConta);
    contas.remove(numeroConta);
    }

    //retorna a lista de transações (extrato) de uma conta específica
    public List<String> obterExtrato(String numeroConta) throws ContaNaoEncontradaException {
        Conta conta = buscarConta(numeroConta);
        //devolve uma cópia da lista, para ninguém mexer direto no original
        return new ArrayList<>(conta.getHistoricoTransacoes());
    } //o extrato é mantido em memória durante a execução do sistema. Não estamos persistindo o histórico de transações na base de dados, apenas o saldo final das contas

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
    //lista todos os clientes conhecidos pelo Banco (em memória)
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }
    //lista todas as contas conhecidas pelo Banco (em memória)
    public List<Conta> listarContas() {
        return new ArrayList<>(contas.values());
    }
}