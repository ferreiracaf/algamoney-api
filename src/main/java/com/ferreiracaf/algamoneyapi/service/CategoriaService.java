package com.ferreiracaf.algamoneyapi.service;

import com.ferreiracaf.algamoneyapi.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listarTodos();

    Categoria criarCategoria(Categoria categoria);

    Categoria buscarPorCodigo(Long codigo);

    void removerCategoria(Long codigo);
}
