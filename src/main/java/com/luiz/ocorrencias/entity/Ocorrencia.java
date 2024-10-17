package com.luiz.ocorrencias.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luiz.ocorrencias.dto.OcorrenciaRequestDTO;
import com.luiz.ocorrencias.enums.StatusOcorrencia;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "ocorrencia", schema = "ocorrencias")
//cria todos getters, seters, allArgsContructor, hashCode e etc
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ocorrencia {

	
	@Column(name = "cod_ocorrencia")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "dat_ocorrencia")
	private LocalDate data;
	
	@Column(name =	"sta_ocorrencia")
	@Enumerated(EnumType.STRING)
	private StatusOcorrencia status; 
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_endereco")
	private Endereco endereco;
	
	
	@OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FotoOcorrencia> fotos = new ArrayList<>();

	
	public Ocorrencia(OcorrenciaRequestDTO data) {
		this.data = data.data();
		this.status = data.status();
		this.cliente = data.cliente();
		this.endereco = data.endereco();
		this.fotos = data.fotos();
		
	}
}
