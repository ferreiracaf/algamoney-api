package com.ferreiracaf.algamoneyapi.service;

import com.ferreiracaf.algamoneyapi.model.Lancamento;
import com.ferreiracaf.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoService{

    Page<Lancamento> listarLancamentos(LancamentoFilter lancamentoFilter, Pageable pageable);

    Lancamento buscarPorCodigo(Long codigo);

    Lancamento cadastrarLancamento(Lancamento lancamento);

    void removerLancamento(Long codigo);
}
