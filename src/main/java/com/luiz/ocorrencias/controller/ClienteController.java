package com.luiz.ocorrencias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.ocorrencias.dto.ClienteRequestDTO;
import com.luiz.ocorrencias.dto.ClienteResponseDTO;
import com.luiz.ocorrencias.entity.Cliente;
import com.luiz.ocorrencias.repository.ClienteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @Operation(summary = "Carrega todos os clientes", description = "Retorna uma lista de clientes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    })
    @GetMapping
    public List<ClienteResponseDTO> loadAll() {
        return repository.findAll().stream().map(ClienteResponseDTO::new).toList();
    }

    @Operation(summary = "Salva um novo cliente", description = "Cadastra um novo cliente no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso")
    })
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> saveCliente(@RequestBody ClienteRequestDTO data) {
        Cliente cliente = new Cliente(data);
        Cliente savedCliente = repository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteResponseDTO(savedCliente));
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados de um cliente existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteRequestDTO data) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNome(data.nome());
        clienteExistente.setDataNascimento(data.dataNascimento());
        clienteExistente.setCpf(data.cpf());
        Cliente updatedCliente = repository.save(clienteExistente);
        return ResponseEntity.ok(new ClienteResponseDTO(updatedCliente));
    }

    @Operation(summary = "Deleta um cliente", description = "Exclui um cliente do sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repository.delete(clienteExistente);
        return ResponseEntity.noContent().build(); 
    }
}
