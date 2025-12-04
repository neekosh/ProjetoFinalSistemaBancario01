package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Conta;
import com.unileste.projetofinal.utilitarios.ConnectionFactory;
import com.unileste.projetofinal.entidades.Cliente;
import com.unileste.projetofinal.entidades.ContaCorrente;
import com.unileste.projetofinal.entidades.ContaPoupanca;
import com.unileste.projetofinal.dao.ClienteDAO;
import com.unileste.projetofinal.dao.ClienteDAOJdbc;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAOJdbc implements ContaDAO {

    @Override
    public void inserir(Conta conta) {
        //descobrir o tipo da conta e valores específicos
        String tipo;
        double limiteChequeEspecial = 0.0;
        double taxaRendMensal = 0.0;

        if (conta instanceof com.unileste.projetofinal.entidades.ContaCorrente) {//pergunta ao Java “esse objeto é uma ContaCorrente?
            tipo = "CORRENTE";
            com.unileste.projetofinal.entidades.ContaCorrente cc =
                    (com.unileste.projetofinal.entidades.ContaCorrente) conta;
            limiteChequeEspecial = cc.getLimiteChequeEspecial(); //se for, fazemos um cast e pegamos o limite do cheque especial
            //taxa continua 0.0
        } else if (conta instanceof com.unileste.projetofinal.entidades.ContaPoupanca) {
            tipo = "POUPANCA";
            com.unileste.projetofinal.entidades.ContaPoupanca cp =
                    (com.unileste.projetofinal.entidades.ContaPoupanca) conta;
            taxaRendMensal = cp.getTaxaRendMensal(); //se for ContaPoupanca, pegamos a taxa de rendimento
            //limite continua 0.0
        } else {
            throw new IllegalArgumentException("Tipo de conta desconhecido para inserção.");
        }

        String sql = "INSERT INTO conta " //String que vai direto pra coluna tipo do banco
                + "(numero, tipo, cpf_cliente, saldo, limite_cheque_especial, taxa_rendimento_mensal) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, conta.getNumero());
            ps.setString(2, tipo);
            ps.setString(3, conta.getProprietario().getCpf());
            ps.setDouble(4, conta.getSaldo());
            ps.setDouble(5, limiteChequeEspecial);
            ps.setDouble(6, taxaRendMensal);

            ps.executeUpdate();
            System.out.println("Conta inserida com sucesso no banco de dados.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir conta: " + e.getMessage());
        }
    }

    @Override
    public Conta buscarPorNumero(String numero) {
        String sql = "SELECT numero, tipo, cpf_cliente, saldo, "
                   + "limite_cheque_especial, taxa_rendimento_mensal "
                   + "FROM conta WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, numero);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    String cpfCliente = rs.getString("cpf_cliente");
                    double saldo = rs.getDouble("saldo");
                    double limite = rs.getDouble("limite_cheque_especial");
                    double taxa = rs.getDouble("taxa_rendimento_mensal");

                    //busca o cliente dono da conta
                    ClienteDAO clienteDAO = new ClienteDAOJdbc();
                    Cliente cliente = clienteDAO.buscarPorCpf(cpfCliente);

                    if (cliente == null) {
                        System.err.println("Cliente não encontrado para a conta: "+numero);
                        return null;
                    }

                    //cria o tipo correto de conta
                    if ("CORRENTE".equalsIgnoreCase(tipo)) {
                        ContaCorrente conta = new ContaCorrente(numero, cliente, limite);
                        conta.setSaldo(saldo);
                        return conta;
                    } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
                        ContaPoupanca conta = new ContaPoupanca(numero, cliente, taxa);
                        conta.setSaldo(saldo);
                        return conta;
                    } else {
                        System.err.println("Tipo de conta desconhecido no banco: "+tipo);
                        return null;
                    }
                } else {
                    //se nenhuma conta com esse número
                    return null;
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar conta por número: "+e.getMessage());
            return null;
        }
    }

    @Override
    public List<Conta> listarTodas() {
        String sql = "SELECT numero, tipo, cpf_cliente, saldo, "
                   +"limite_cheque_especial, taxa_rendimento_mensal "
                   +"FROM conta";

        List<Conta> contas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ClienteDAO clienteDAO = new ClienteDAOJdbc();

            while (rs.next()) {
                String numero = rs.getString("numero");
                String tipo = rs.getString("tipo");
                String cpfCliente = rs.getString("cpf_cliente");
                double saldo = rs.getDouble("saldo");
                double limite = rs.getDouble("limite_cheque_especial");
                double taxa = rs.getDouble("taxa_rendimento_mensal");

                Cliente cliente = clienteDAO.buscarPorCpf(cpfCliente);
                if (cliente == null) {
                    System.err.println("Cliente não encontrado para a conta: "+numero);
                    continue;
                }

                if ("CORRENTE".equalsIgnoreCase(tipo)) {
                    ContaCorrente conta = new ContaCorrente(numero, cliente, limite);
                    conta.setSaldo(saldo);
                    contas.add(conta);
                } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
                    ContaPoupanca conta = new ContaPoupanca(numero, cliente, taxa);
                    conta.setSaldo(saldo);
                    contas.add(conta);
                } else {
                    System.err.println("Tipo de conta desconhecido no banco: "+tipo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contas: "+e.getMessage());
        }
        return contas;
    }

    @Override
    public void atualizar(Conta conta) {
        String tipo;
        double limiteChequeEspecial = 0.0;
        double taxaRendMensal = 0.0;

        if (conta instanceof ContaCorrente) {
            tipo = "CORRENTE";
            ContaCorrente cc = (ContaCorrente) conta;
            limiteChequeEspecial = cc.getLimiteChequeEspecial();
        } else if (conta instanceof ContaPoupanca) {
            tipo = "POUPANCA";
            ContaPoupanca cp = (ContaPoupanca) conta;
            taxaRendMensal = cp.getTaxaRendMensal();
        } else {
            throw new IllegalArgumentException("Tipo de conta desconhecido para atualização.");
        }

        String sql = "UPDATE conta SET tipo = ?, cpf_cliente = ?, saldo = ?, "
                   +"limite_cheque_especial = ?, taxa_rendimento_mensal = ? "
                   +"WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipo);
            ps.setString(2, conta.getProprietario().getCpf());
            ps.setDouble(3, conta.getSaldo());
            ps.setDouble(4, limiteChequeEspecial);
            ps.setDouble(5, taxaRendMensal);
            ps.setString(6, conta.getNumero());

            int linhas = ps.executeUpdate();

            if (linhas == 0) {
                System.err.println("Nenhuma conta encontrada para atualizar: "+conta.getNumero());
            } else {
                System.out.println("Conta atualizada com sucesso.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta: "+e.getMessage());
        }
    }

    @Override
    public void remover(String numero) {
        String sql = "DELETE FROM conta WHERE numero = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numero);
            int linhas = ps.executeUpdate();

            if (linhas == 0) {
                System.err.println("Nenhuma conta encontrada para remover: "+numero);
            } else {
                System.out.println("Conta removida com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao remover conta: "+e.getMessage());
        }
    }
}
