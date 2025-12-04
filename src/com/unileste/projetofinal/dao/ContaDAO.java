package com.unileste.projetofinal.dao;

import com.unileste.projetofinal.entidades.Conta;
import java.util.List;

public interface ContaDAO {

    //criar
    void inserir(Conta conta);

    //buscar uma conta
    Conta buscarPorNumero(String numero);

    //listar todas as conta
    List<Conta> listarTodas();

    //fazer update
    void atualizar(Conta conta);

    //deletar
    void remover(String numero);
}
