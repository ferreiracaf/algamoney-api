package com.ferreiracaf.algamoneyapi.repository.lancamento;

import com.ferreiracaf.algamoneyapi.model.Lancamento;
import com.ferreiracaf.algamoneyapi.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable Pageable);
}
