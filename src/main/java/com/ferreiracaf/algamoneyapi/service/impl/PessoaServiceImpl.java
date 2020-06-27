package com.ferreiracaf.algamoneyapi.service.impl;

import com.ferreiracaf.algamoneyapi.model.Pessoa;
import com.ferreiracaf.algamoneyapi.repository.PessoaRepository;
import com.ferreiracaf.algamoneyapi.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> buscarTodos(){
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoa(Long codigo){
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        if (pessoaSalva.isPresent()) return pessoaSalva.get();
        throw new EmptyResultDataAccessException(1);
    }

    public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa){
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        if (pessoaSalva.isPresent()){
            BeanUtils.copyProperties(pessoa, pessoaSalva.get(), "codigo");
            pessoaRepository.save(pessoaSalva.get());
            return pessoaSalva.get();
        }
        throw new EmptyResultDataAccessException(1);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = this.buscarPessoa(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public void apagarPessoa(Long codigo){
        pessoaRepository.deleteById(codigo);
    }
}
