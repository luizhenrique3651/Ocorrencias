package com.luiz.ocorrencias.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "foto", schema = "ocorrencias")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FotoOcorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_foto_ocorrencia")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "cod_ocorrencia", nullable = false) 
    private Ocorrencia ocorrencia;

    @Column(name = "dta_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "dsc_path_bucket", nullable = false)
    private String pathBucket;

    @Column(name = "dsc_hash", nullable = false)
    private String hash;
}
