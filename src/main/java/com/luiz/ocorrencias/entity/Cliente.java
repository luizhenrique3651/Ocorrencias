package com.luiz.ocorrencias.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiz.ocorrencias.dto.ClienteRequestDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente", schema = "ocorrencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	
	@Column(name = "cod_cliente")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nme_cliente")
	private String nome;
	
	@Column(name = "dta_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "nro_cpf")
	private String cpf;
	
	@Column(name = "dta_criacao")
	private LocalDate dataCriacao;
	@JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
    
    public Cliente(ClienteRequestDTO data) {
    	this.nome = data.nome();
    	this.dataNascimento = data.dataNascimento();
    	this.cpf = data.cpf();
    	this.dataCriacao = data.dataCriacao();
    	this.ocorrencias = data.ocorrencias();
    }
 
 
}
