package com.luiz.ocorrencias.repository;

import com.luiz.ocorrencias.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	public Cliente findByCpf(String cpf);
}
