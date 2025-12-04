package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Cliente;
import java.util.List;

public interface ClienteDAO {

    void inserir(Cliente cliente);

    Cliente buscarPorCpf(String cpf);

    List<Cliente> listarTodos();

    void atualizar(Cliente cliente);

    void remover(String cpf);
}
