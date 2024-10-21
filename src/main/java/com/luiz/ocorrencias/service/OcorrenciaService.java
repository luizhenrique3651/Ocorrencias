package com.luiz.ocorrencias.service;

import com.luiz.ocorrencias.entity.Ocorrencia;
import com.luiz.ocorrencias.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    public OcorrenciaService(OcorrenciaRepository ocorrenciaRepository) {
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public List<Ocorrencia> findAll() {
        return ocorrenciaRepository.findAll();
    }

    public Optional<Ocorrencia> findById(Long id) {
        return ocorrenciaRepository.findById(id);
    }

    public Ocorrencia save(Ocorrencia ocorrencia) {
        return ocorrenciaRepository.save(ocorrencia);
    }

    public Ocorrencia update(Long id, Ocorrencia ocorrenciaAtualizada) {
        Optional<Ocorrencia> ocorrenciaExistente = ocorrenciaRepository.findById(id);
        if (ocorrenciaExistente.isPresent()) {
            Ocorrencia ocorrencia = ocorrenciaExistente.get();
            ocorrencia.setData(ocorrenciaAtualizada.getData());
            ocorrencia.setStatus(ocorrenciaAtualizada.getStatus());
            ocorrencia.setCliente(ocorrenciaAtualizada.getCliente());
            ocorrencia.setEndereco(ocorrenciaAtualizada.getEndereco());
            ocorrencia.setFotos(ocorrenciaAtualizada.getFotos());
            return ocorrenciaRepository.save(ocorrencia);
        } else {
            throw new RuntimeException("Ocorrência não encontrada com o ID: " + id);
        }
    }

    public void delete(Long id) {
        if (ocorrenciaRepository.existsById(id)) {
            ocorrenciaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Ocorrência não encontrada com o ID: " + id);
        }
    }
}
