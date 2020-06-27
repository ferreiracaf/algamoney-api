package com.ferreiracaf.algamoneyapi.service;

import com.ferreiracaf.algamoneyapi.model.Pessoa;

import java.util.List;

public interface PessoaService {

    Pessoa salvarPessoa(Pessoa pessoa);

    List<Pessoa> buscarTodos();

    Pessoa buscarPessoa(Long codigo);

    Pessoa atualizarPessoa(Long codigo, Pessoa pessoa);

    void atualizarPropriedadeAtivo(Long codigo, Boolean ativo);

    void apagarPessoa(Long codigo);
}
