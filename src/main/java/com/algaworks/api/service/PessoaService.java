package com.algaworks.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.api.model.Pessoa;
import com.algaworks.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public Pessoa atualizar(Long id, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoa(id);
		// ignora o id
		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		return repository.save(pessoaSalva);
	}


	public void atualizarPropriedadeAtivo(Long id, Boolean ativo) {
		Pessoa pessoa = buscarPessoa(id);
		pessoa.setAtivo(ativo);
		repository.save(pessoa);
	}
	
	private Pessoa buscarPessoa(Long id) {
		Pessoa pessoaSalva = repository.findOne(id);
		if (pessoaSalva == null) {
			// já vai cair lá no exception handler e retornar not found
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
