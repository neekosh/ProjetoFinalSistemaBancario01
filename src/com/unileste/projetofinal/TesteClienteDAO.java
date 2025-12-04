package com.unileste.projetofinal;

import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ClienteDAOJdbc;
import com.unileste.projetofinal.entidades.Cliente;

import java.util.List;

public class TesteClienteDAO {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAOJdbc();

        // 1) INSERIR UM NOVO CLIENTE
        System.out.println("=== 1) INSERIR CLIENTE ===");
        Cliente novo = new Cliente(
                "Maria da Silva",
                "11122233344",
                "Rua A, 10"
        );
        clienteDAO.inserir(novo);

        // 2) BUSCAR POR CPF
        System.out.println("\n=== 2) BUSCAR POR CPF ===");
        Cliente encontrado = clienteDAO.buscarPorCpf("11122233344");
        if (encontrado != null) {
            System.out.println("Encontrado: " + encontrado.getNome()
                    + " - " + encontrado.getCpf()
                    + " - " + encontrado.getEndereco());
        } else {
            System.out.println("Cliente não encontrado.");
        }

        // 3) LISTAR TODOS
        System.out.println("\n=== 3) LISTAR TODOS ===");
        List<Cliente> lista = clienteDAO.listarTodos();
        for (Cliente c : lista) {
            System.out.println(c.getCpf() + " | " + c.getNome() + " | " + c.getEndereco());
        }

        // 4) ATUALIZAR ENDEREÇO DO CLIENTE
        System.out.println("\n=== 4) ATUALIZAR CLIENTE ===");
        if (encontrado != null) {
            encontrado.setEndereco("Rua B, 99");
            clienteDAO.atualizar(encontrado);
        }

        // 5) BUSCAR DE NOVO PARA VER SE ATUALIZOU
        System.out.println("\n=== 5) BUSCAR APÓS ATUALIZAR ===");
        Cliente atualizado = clienteDAO.buscarPorCpf("11122233344");
        if (atualizado != null) {
            System.out.println("Novo endereço: " + atualizado.getEndereco());
        }

        // 6) REMOVER CLIENTE
        System.out.println("\n=== 6) REMOVER CLIENTE ===");
        clienteDAO.remover("11122233344");

        // 7) LISTAR DE NOVO PARA VER COMO FICOU
        System.out.println("\n=== 7) LISTAR DEPOIS DA REMOÇÃO ===");
        lista = clienteDAO.listarTodos();
        for (Cliente c : lista) {
            System.out.println(c.getCpf()+" | "+c.getNome()+" | "+c.getEndereco());
        }

        System.out.println("\nFim do teste de CRUD de Cliente.");
    }
}
