package com.ferreiracaf.algamoneyapi.service.impl;

import com.ferreiracaf.algamoneyapi.model.Categoria;
import com.ferreiracaf.algamoneyapi.repository.CategoriaRepository;
import com.ferreiracaf.algamoneyapi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> listarTodos(){
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria criarCategoria(Categoria categoria){
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaSalva;
    }

    @Override
    public Categoria buscarPorCodigo(Long codigo) {
        return categoriaRepository.findById(codigo).orElse(null);
    }

    @Override
    public void removerCategoria(Long codigo) {
        categoriaRepository.deleteById(codigo);
    }
}
