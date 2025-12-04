package com.unileste.projetofinal;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ClienteDAOJdbc;
import com.unileste.projetofinal.dao.ContaDAO;
import com.unileste.projetofinal.dao.ContaDAOJdbc;
import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;
import java.util.List;

public class TesteContaDAO {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAOJdbc();
        ContaDAO contaDAO = new ContaDAOJdbc();

        // 0) Garantir que existe um cliente no banco
        System.out.println("=== 0) GARANTIR CLIENTE ===");
        String cpf = "12345678900";
        Cliente cliente = clienteDAO.buscarPorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado, criando um novo...");

            cliente = new Cliente(
                    "Fulano de Tal",
                    cpf,
                    "Rua dos Testes, 123"
            );
            clienteDAO.inserir(cliente);
        } else {
            System.out.println("Cliente já existe: " + cliente.getNome());
        }

        // 1) INSERIR DUAS CONTAS (corrente e poupança), SE AINDA NÃO EXISTIREM
        System.out.println("\n=== 1) INSERIR CONTAS ===");

        // Conta Corrente 0001-1
        Conta contaExistente = contaDAO.buscarPorNumero("0001-1");
        if (contaExistente == null) {
            System.out.println("Conta 0001-1 não existe, criando...");
            ContaCorrente cc = new ContaCorrente(
                    "0001-1",
                    cliente,
                    500.0   // limite cheque especial
            );
            contaDAO.inserir(cc);
        } else {
            System.out.println("Conta 0001-1 já existe, não vou inserir de novo.");
        }

        // Conta Poupança 0002-1
        Conta contaExistente2 = contaDAO.buscarPorNumero("0002-1");
        if (contaExistente2 == null) {
            System.out.println("Conta 0002-1 não existe, criando...");
            ContaPoupanca cp = new ContaPoupanca(
                    "0002-1",
                    cliente,
                    0.01    // 1% de rendimento mensal
            );
            contaDAO.inserir(cp);
        } else {
            System.out.println("Conta 0002-1 já existe, não vou inserir de novo.");
        }


        // 2) BUSCAR UMA CONTA POR NÚMERO
        System.out.println("\n=== 2) BUSCAR POR NÚMERO (0001-1) ===");
        Conta contaEncontrada = contaDAO.buscarPorNumero("0001-1");
        if (contaEncontrada != null) {
            System.out.println("Conta encontrada: "
                    + contaEncontrada.getNumero()
                    + " | Saldo: " + contaEncontrada.getSaldo()
                    + " | Cliente: " + contaEncontrada.getProprietario().getNome());
        } else {
            System.out.println("Conta não encontrada.");
        }

        // 3) LISTAR TODAS AS CONTAS
        System.out.println("\n=== 3) LISTAR TODAS AS CONTAS ===");
        List<Conta> contas = contaDAO.listarTodas();
        for (Conta c : contas) {
            System.out.println(c.getNumero()
                    + " | saldo: " + c.getSaldo()
                    + " | cliente: " + c.getProprietario().getNome());
        }

        // 4) ATUALIZAR SALDO DE UMA CONTA (simulando um depósito)
        System.out.println("\n=== 4) ATUALIZAR SALDO DA CONTA 0001-1 ===");
        if (contaEncontrada != null) {
            double saldoAtual = contaEncontrada.getSaldo();
            double valorDeposito = 200.0;

            contaEncontrada.setSaldo(saldoAtual + valorDeposito);
            contaDAO.atualizar(contaEncontrada);

            System.out.println("Depósito simulado de R$ " + valorDeposito
                    + ". Saldo anterior: " + saldoAtual
                    + " | Novo saldo salvo no banco.");
        }

        // 5) BUSCAR DE NOVO PARA VER O NOVO SALDO
        System.out.println("\n=== 5) BUSCAR CONTA 0001-1 APÓS ATUALIZAÇÃO ===");
        Conta contaAtualizada = contaDAO.buscarPorNumero("0001-1");
        if (contaAtualizada != null) {
            System.out.println("Saldo atual da conta 0001-1: "
                    + contaAtualizada.getSaldo());
        }

        // 6) REMOVER UMA CONTA (por exemplo, a poupança 0002-1)
        System.out.println("\n=== 6) REMOVER CONTA 0002-1 ===");
        contaDAO.remover("0002-1");

        // 7) LISTAR TODAS NOVAMENTE
        System.out.println("\n=== 7) LISTAR CONTAS APÓS REMOÇÃO ===");
        contas = contaDAO.listarTodas();
        for (Conta c : contas) {
            System.out.println(c.getNumero()
                    + " | saldo: " + c.getSaldo()
                    + " | cliente: " + c.getProprietario().getNome());
        }

        System.out.println("\nFim do teste de CRUD de Conta.");
    }
}

