package com.algaworks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
