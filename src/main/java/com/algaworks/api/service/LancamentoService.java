package com.algaworks.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.api.model.Lancamento;
import com.algaworks.api.model.Pessoa;
import com.algaworks.api.repository.LancamentoRepository;
import com.algaworks.api.repository.PessoaRepository;
import com.algaworks.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Lancamento cadastrar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getId());
		if (pessoa == null || pessoa.isInativa()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return repository.save(lancamento);
	}
	
}
