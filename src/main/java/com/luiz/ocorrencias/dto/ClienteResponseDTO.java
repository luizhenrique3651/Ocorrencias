package com.luiz.ocorrencias.dto;

import java.time.LocalDate;
import java.util.List;

import com.luiz.ocorrencias.entity.Cliente;
import com.luiz.ocorrencias.entity.Ocorrencia;

public record ClienteResponseDTO(Long id, String nome, LocalDate dataNascimento, String cpf, LocalDate dataCriacao,
		List<Ocorrencia> ocorrencias) {

	public ClienteResponseDTO(Cliente cliente) {
		this(cliente.getId(), cliente.getNome(), cliente.getDataNascimento(), cliente.getCpf(),
				cliente.getDataCriacao(), cliente.getOcorrencias());
	}
}
