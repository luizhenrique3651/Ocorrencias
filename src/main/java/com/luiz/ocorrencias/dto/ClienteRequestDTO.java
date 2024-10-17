package com.luiz.ocorrencias.dto;

import java.time.LocalDate;
import java.util.List;

import com.luiz.ocorrencias.entity.Ocorrencia;

import jakarta.validation.constraints.Pattern;

public record ClienteRequestDTO(String nome, LocalDate dataNascimento, @Pattern(regexp = "\\d{11}", message = "CPF inválido. Deve conter 11 dígitos.")
String cpf, LocalDate dataCriacao, List<Ocorrencia> ocorrencias) {

}
