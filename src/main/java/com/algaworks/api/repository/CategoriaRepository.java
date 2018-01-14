package com.algaworks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
