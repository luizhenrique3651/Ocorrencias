package com.luiz.ocorrencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.ocorrencias.dto.EnderecoRequestDTO;
import com.luiz.ocorrencias.dto.EnderecoResponseDTO;
import com.luiz.ocorrencias.entity.Endereco;
import com.luiz.ocorrencias.repository.EnderecoRepository;
import com.luiz.ocorrencias.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    EnderecoService repository;

    @Operation(summary = "Carrega todos os endereços", description = "Retorna uma lista de endereços cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso")
    })
    @GetMapping
    public List<EnderecoResponseDTO> loadAll() {
        return repository.findAll().stream().map(EnderecoResponseDTO::new).toList();
    }

    @Operation(summary = "Salva um novo endereço", description = "Cadastra um novo endereço no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso")
    })
    @PostMapping
    public ResponseEntity<EnderecoResponseDTO> saveEndereco(@RequestBody EnderecoRequestDTO data) {
        Endereco endereco = new Endereco(data);
        Endereco savedEndereco = repository.save(endereco);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new EnderecoResponseDTO(savedEndereco));
    }

    @Operation(summary = "Atualiza um endereço", description = "Atualiza os dados de um endereço existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDTO data) {
        Endereco enderecoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        enderecoExistente.setLogradouro(data.logradouro());
        enderecoExistente.setBairro(data.bairro());
        enderecoExistente.setCep(data.cep());
        enderecoExistente.setCidade(data.cidade());
        enderecoExistente.setEstado(data.estado());
        Endereco updatedEndereco = repository.save(enderecoExistente);
        return ResponseEntity.ok(new EnderecoResponseDTO(updatedEndereco));
    }

    @Operation(summary = "Deleta um endereço", description = "Exclui um endereço do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        Endereco enderecoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        repository.delete(enderecoExistente.getId());
        return ResponseEntity.noContent().build(); 
    }
}
