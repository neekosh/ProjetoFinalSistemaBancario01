package com.unileste.projetofinal;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.operacoes.Banco;
import com.unileste.projetofinal.utilitarios.ClienteNaoEncontradoException;
import com.unileste.projetofinal.utilitarios.ContaNaoEncontradaException;
import com.unileste.projetofinal.utilitarios.SaldoInsuficienteException;

import java.util.Map;

public class TesteBanco {

    public static void main(String[] args) {

        // 0) Criar o objeto Banco (ele já vai carregar os dados do banco de dados)
        Banco banco = new Banco("Banco Unileste");

        // 1) Cadastrar um cliente novo
        System.out.println("=== 1) CADASTRAR CLIENTE ===");
        Cliente cliente = new Cliente(
                "João Teste",
                "99988877766",
                "Rua dos Bancos, 10"
        );

        try {
            banco.cadastrarCliente(cliente);
            System.out.println("Cliente cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }

        // 2) Abrir uma conta corrente e uma poupança para esse cliente
        System.out.println("\n=== 2) ABRIR CONTAS ===");
        String numeroContaCorrente = null;
        String numeroContaPoupanca = null;

        try {
            Conta contaCorrente = banco.abrirConta(cliente, "corrente", 500.0);
            numeroContaCorrente = contaCorrente.getNumero();
            System.out.println("Conta corrente criada com número: " + numeroContaCorrente);

            Conta contaPoupanca = banco.abrirConta(cliente, "poupanca", 0.01);
            numeroContaPoupanca = contaPoupanca.getNumero();
            System.out.println("Conta poupança criada com número: " + numeroContaPoupanca);

        } catch (ClienteNaoEncontradoException e) {
            System.err.println("Erro: cliente não encontrado ao abrir conta: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de dados ao abrir conta: " + e.getMessage());
        }

        // 3) Realizar um depósito na conta corrente
        System.out.println("\n=== 3) REALIZAR DEPÓSITO NA CORRENTE ===");
        try {
            if (numeroContaCorrente != null) {
                banco.realizarDeposito(numeroContaCorrente, 300.0);
                Conta conta = banco.buscarConta(numeroContaCorrente);
                System.out.println("Saldo da conta " + numeroContaCorrente
                        + " após depósito: " + conta.getSaldo());
            } else {
                System.out.println("Conta corrente ainda não foi criada, não dá para depositar.");
            }
        } catch (ContaNaoEncontradaException e) {
            System.err.println("Erro: conta não encontrada ao depositar: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de valor ao depositar: " + e.getMessage());
        }

        // 4) Realizar um saque na conta corrente
        System.out.println("\n=== 4) REALIZAR SAQUE NA CORRENTE ===");
        try {
            if (numeroContaCorrente != null) {
                banco.realizarSaque(numeroContaCorrente, 100.0);
                Conta conta = banco.buscarConta(numeroContaCorrente);
                System.out.println("Saldo da conta " + numeroContaCorrente
                        + " após saque: " + conta.getSaldo());
            }
        } catch (ContaNaoEncontradaException e) {
            System.err.println("Erro: conta não encontrada ao sacar: " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.err.println("Erro: saldo insuficiente ao sacar: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de valor ao sacar: " + e.getMessage());
        }

        // 5) Realizar uma transferência da conta corrente para a poupança
        System.out.println("\n=== 5) REALIZAR TRANSFERÊNCIA CORRENTE → POUPANÇA ===");
        try {
            if (numeroContaCorrente != null && numeroContaPoupanca != null) {
                banco.realizarTransferencias(numeroContaCorrente, numeroContaPoupanca, 50.0);

                Conta contaOrigem = banco.buscarConta(numeroContaCorrente);
                Conta contaDestino = banco.buscarConta(numeroContaPoupanca);

                System.out.println("Saldo da conta corrente (" + numeroContaCorrente
                        + ") após transferência: " + contaOrigem.getSaldo());
                System.out.println("Saldo da conta poupança (" + numeroContaPoupanca
                        + ") após receber transferência: " + contaDestino.getSaldo());
            } else {
                System.out.println("Não é possível transferir: uma das contas não foi criada.");
            }
        } catch (ContaNaoEncontradaException e) {
            System.err.println("Erro: conta não encontrada na transferência: " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.err.println("Erro: saldo insuficiente na transferência: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de valor na transferência: " + e.getMessage());
        }

        // 6) Listar todas as contas conhecidas pelo banco (map em memória)
        System.out.println("\n=== 6) LISTAR TODAS AS CONTAS DO BANCO (MAP) ===");
        Map<String, Conta> mapaContas = banco.getContas();
        for (Conta c : mapaContas.values()) {
            System.out.println("Conta " + c.getNumero()
                    + " | Saldo: " + c.getSaldo()
                    + " | Cliente: " + c.getProprietario().getNome());
        }
        
        // 7) Mostrar extrato da conta corrente
        System.out.println("\n=== 7) EXTRATO DA CONTA CORRENTE ===");
        try {
            if (numeroContaCorrente != null) {
                var extrato = banco.obterExtrato(numeroContaCorrente);
                if (extrato.isEmpty()) {
                    System.out.println("Nenhuma transação registrada para a conta " + numeroContaCorrente);
                } else {
                    for (String linha : extrato) {
                        System.out.println(linha);
                    }
                }
            } else {
                System.out.println("Conta corrente não foi criada, não há extrato.");
            }
        } catch (ContaNaoEncontradaException e) {
            System.err.println("Erro ao obter extrato: " + e.getMessage());
        }

        System.out.println("\nFim do teste do Banco.");

        System.out.println("\nFim do teste do Banco.");
    }
}
