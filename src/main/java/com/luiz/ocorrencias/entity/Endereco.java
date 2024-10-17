package com.luiz.ocorrencias.entity;

import com.luiz.ocorrencias.dto.EnderecoRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco", schema = "ocorrencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	
	@Column(name = "cod_endereco")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nme_logradouro")
	private String logradouro;
	
	@Column(name = "nme_bairro")
	private String bairro;
	
	@Column(name = "nro_cep")
	private String cep;
	
	@Column(name = "nme_cidade")
	private String cidade;
	
	@Column(name = "nme_estado")
	private String estado;
	
	public Endereco(EnderecoRequestDTO data) {
		this.logradouro = data.logradouro();
		this.bairro = data.bairro();
		this.cep = data.cep();
		this.cidade = data.cidade();
		this.estado = data.estado();
	}
}
