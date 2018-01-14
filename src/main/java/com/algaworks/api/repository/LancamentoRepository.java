package com.algaworks.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByDescricaoAndDataVencimentoBetween(String descricao, LocalDate dataVencimentoDe, LocalDate dataVencimentoAte);
	
}
