package com.ferreiracaf.algamoneyapi.service.impl;

import com.ferreiracaf.algamoneyapi.model.Lancamento;
import com.ferreiracaf.algamoneyapi.model.Pessoa;
import com.ferreiracaf.algamoneyapi.repository.LancamentoRepository;
import com.ferreiracaf.algamoneyapi.repository.PessoaRepository;
import com.ferreiracaf.algamoneyapi.repository.filter.LancamentoFilter;
import com.ferreiracaf.algamoneyapi.service.LancamentoService;
import com.ferreiracaf.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public Page<Lancamento> listarLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    @Override
    public Lancamento buscarPorCodigo(Long codigo) {
        return lancamentoRepository.findById(codigo).orElse(null);
    }

    @Override
    public Lancamento cadastrarLancamento(Lancamento lancamento) {
        Optional<Pessoa> byId = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (byId.isPresent())
            if (byId.get().getAtivo())
                return lancamentoRepository.save(lancamento);
        throw new PessoaInexistenteOuInativaException();
    }

    @Override
    public void removerLancamento(Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

}
