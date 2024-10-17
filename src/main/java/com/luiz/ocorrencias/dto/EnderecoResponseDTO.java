package com.luiz.ocorrencias.dto;

import com.luiz.ocorrencias.entity.Endereco;

public record EnderecoResponseDTO(Long id, String logradouro, String bairro, String cep, String cidade, String estado) {

	public EnderecoResponseDTO(Endereco endereco) {
		this(endereco.getId(), endereco.getLogradouro(), endereco.getBairro(),endereco.getCep(), endereco.getCidade(), endereco.getEstado());
	}
}
