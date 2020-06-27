package com.ferreiracaf.algamoneyapi.repository;

import com.ferreiracaf.algamoneyapi.model.Lancamento;
import com.ferreiracaf.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository <Lancamento, Long>, LancamentoRepositoryQuery {
}
