package com.luiz.ocorrencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.ocorrencias.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	public Endereco findByCep(String cep);
}
