package com.luiz.ocorrencias.service;

import com.luiz.ocorrencias.entity.Endereco;
import com.luiz.ocorrencias.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco findByCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco update(Long id, Endereco enderecoAtualizado) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();
            endereco.setLogradouro(enderecoAtualizado.getLogradouro());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setCidade(enderecoAtualizado.getCidade());
            endereco.setEstado(enderecoAtualizado.getEstado());
            return enderecoRepository.save(endereco);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }
}
