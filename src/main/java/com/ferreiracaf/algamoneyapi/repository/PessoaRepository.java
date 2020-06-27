package com.ferreiracaf.algamoneyapi.repository;

import com.ferreiracaf.algamoneyapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
