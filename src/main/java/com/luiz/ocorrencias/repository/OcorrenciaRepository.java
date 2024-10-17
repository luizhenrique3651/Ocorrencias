package com.luiz.ocorrencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.ocorrencias.entity.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>{

}
