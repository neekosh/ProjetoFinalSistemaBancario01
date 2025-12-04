package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.utilitarios.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class ClienteDAOJdbc implements ClienteDAO {

    @Override
    public void inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (cpf, nome, endereco) VALUES (?, ?, ?)";

        //try-with-resources: serve para o Java fecha a conexão automaticamente
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getEndereco());

            ps.executeUpdate();
            System.out.println("Cliente inserido com sucesso no banco de dados.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

        @Override
    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT cpf, nome, endereco FROM cliente WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { //evita SQL injection e facilita passar parâmetros

            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) { //é tipo um “cursor” que aponta para as linhas retornadas

                if (rs.next()) { //se tem pelo menos uma linha, ele vai pra primeira linha e podemos ler os dados
                    String nome = rs.getString("nome");
                    String cpfBanco = rs.getString("cpf");
                    String endereco = rs.getString("endereco");
                    //se não tem linha, o next() já volta false e sabemos que não achou
                    Cliente cliente = new Cliente(nome, cpfBanco, endereco);
                    return cliente;
                } else { 
                    return null; //quando não encontra, o DAO devolve null
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar cliente por CPF: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        String sql = "SELECT cpf, nome, endereco FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) { //passa por cada linha retornada
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");

                Cliente cliente = new Cliente(nome, cpf, endereco);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }


    @Override
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, endereco = ? WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEndereco());
            ps.setString(3, cliente.getCpf());

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas == 0) {
                System.err.println("Nenhum cliente encontrado para atualizar com CPF: "+cliente.getCpf());
            } else {
                System.out.println("Cliente atualizado com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: "+e.getMessage());
        }
    }

    @Override
    public void remover(String cpf) {
        String sql = "DELETE FROM cliente WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cpf);

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas == 0) {
                System.err.println("Nenhum cliente encontrado para remover com CPF: "+cpf);
            } else {
                System.out.println("Cliente removido com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover cliente: "+e.getMessage());
        }
    }
}
