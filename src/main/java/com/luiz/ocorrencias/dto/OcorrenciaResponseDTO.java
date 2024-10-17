package com.luiz.ocorrencias.dto;

import java.time.LocalDate;

import com.luiz.ocorrencias.entity.Cliente;
import com.luiz.ocorrencias.entity.Endereco;
import com.luiz.ocorrencias.entity.Ocorrencia;
import com.luiz.ocorrencias.enums.StatusOcorrencia;

public record OcorrenciaResponseDTO(Long id, LocalDate data, StatusOcorrencia status, Cliente cliente,
		Endereco endereco) {

	public OcorrenciaResponseDTO(Ocorrencia ocorrencia) {
		this(ocorrencia.getId(), ocorrencia.getData(), ocorrencia.getStatus(), ocorrencia.getCliente(),
				ocorrencia.getEndereco());
	}
}
