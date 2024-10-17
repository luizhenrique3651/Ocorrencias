package com.luiz.ocorrencias.dto;

import java.time.LocalDate;
import java.util.List;

import com.luiz.ocorrencias.entity.Cliente;
import com.luiz.ocorrencias.entity.Endereco;
import com.luiz.ocorrencias.entity.FotoOcorrencia;
import com.luiz.ocorrencias.enums.StatusOcorrencia;

public record OcorrenciaRequestDTO(LocalDate data, StatusOcorrencia status, Cliente cliente, Endereco endereco, List<FotoOcorrencia> fotos) {

}
