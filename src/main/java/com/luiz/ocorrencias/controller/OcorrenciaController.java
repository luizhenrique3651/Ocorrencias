package com.luiz.ocorrencias.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.ocorrencias.dto.OcorrenciaRequestDTO;
import com.luiz.ocorrencias.dto.OcorrenciaResponseDTO;
import com.luiz.ocorrencias.entity.Cliente;
import com.luiz.ocorrencias.entity.Endereco;
import com.luiz.ocorrencias.entity.Ocorrencia;
import com.luiz.ocorrencias.enums.StatusOcorrencia;
import com.luiz.ocorrencias.repository.ClienteRepository;
import com.luiz.ocorrencias.repository.EnderecoRepository;
import com.luiz.ocorrencias.repository.OcorrenciaRepository;
import com.luiz.ocorrencias.service.ClienteService;
import com.luiz.ocorrencias.service.EnderecoService;
import com.luiz.ocorrencias.service.OcorrenciaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	@Autowired
	OcorrenciaService repository;
	@Autowired
	ClienteService clienteRepo;
	@Autowired
	EnderecoService enderecoRepo;
	
	@Operation(summary = "Carrega todas as ocorrências", description = "Retorna uma lista de ocorrências cadastradas.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de ocorrências retornada com sucesso")
	})
	@GetMapping
	public List<OcorrenciaResponseDTO> loadAll() {
		return repository.findAll().stream().map(OcorrenciaResponseDTO::new).toList();
	}
	
    @Operation(summary = "Lista ocorrências filtradas", description = "Lista ocorrências com dados do cliente e endereço, permitindo filtros e ordenação.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de ocorrências filtrada retornada com sucesso")
    })
    @GetMapping("/filtradas")
    public ResponseEntity<List<OcorrenciaResponseDTO>> getOcorrenciasFiltradas(
            @RequestParam(required = false) String nomeCliente,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) LocalDate dataOcorrencia,
            @RequestParam(required = false) String cidade,
            @RequestParam(defaultValue = "asc") String ordemData,
            @RequestParam(defaultValue = "asc") String ordemCidade) {
        
        List<Ocorrencia> ocorrencias = repository.findAll();
        
        List<Ocorrencia> filtradas = ocorrencias.stream()
                .filter(o -> (nomeCliente == null || o.getCliente().getNome().toLowerCase().contains(nomeCliente.toLowerCase())) &&
                             (cpf == null || o.getCliente().getCpf().equals(cpf)) &&
                             (dataOcorrencia == null || o.getData().equals(dataOcorrencia)) &&
                             (cidade == null || o.getEndereco().getCidade().equalsIgnoreCase(cidade)))
                .collect(Collectors.toList());

        filtradas.sort((o1, o2) -> {
            int compare = ordemData.equals("desc") ? o2.getData().compareTo(o1.getData()) : o1.getData().compareTo(o2.getData());
            if (compare == 0) {
                return ordemCidade.equals("desc") ? o2.getEndereco().getCidade().compareTo(o1.getEndereco().getCidade()) :
                        o1.getEndereco().getCidade().compareTo(o2.getEndereco().getCidade());
            }
            return compare;
        });

        return ResponseEntity.ok(filtradas.stream().map(OcorrenciaResponseDTO::new).collect(Collectors.toList()));
    }

	@Operation(summary = "Salva uma nova ocorrência", description = "Cadastra uma nova ocorrência no sistema.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Ocorrência cadastrada com sucesso")
	})
	@PostMapping
	public ResponseEntity<OcorrenciaResponseDTO> saveOcorrencia(@RequestBody OcorrenciaRequestDTO data) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setData(data.data());
		ocorrencia.setStatus(data.status());
        Ocorrencia savedOcorrencia = repository.save(ocorrencia);
        
        Cliente cliente = data.cliente();
        Cliente cliExistente = clienteRepo.findByCpf(cliente.getCpf());
        cliente.getOcorrencias().add(savedOcorrencia);
        
        if(cliExistente != null) {
        	cliExistente.getOcorrencias().add(savedOcorrencia);
        	clienteRepo.save(cliExistente);
        	savedOcorrencia.setCliente(cliExistente);
        } else {
        	clienteRepo.save(cliente);
        	savedOcorrencia.setCliente(cliente);
        }
        
        Endereco endereco = data.endereco();
        Endereco endExistente = enderecoRepo.findByCep(endereco.getCep());
        
        if(endExistente != null) {
        	savedOcorrencia.setEndereco(endExistente);
        } else {
        	Endereco savedEndereco = enderecoRepo.save(endereco);
        	savedOcorrencia.setEndereco(savedEndereco);
        }
        
        Ocorrencia ocorrenciaCompleta = repository.save(savedOcorrencia);
        return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(summary = "Atualiza uma ocorrência", description = "Atualiza os dados de uma ocorrência existente.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Ocorrência atualizada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Ocorrência não encontrada")
	})
	@PutMapping("/{id}")
	public ResponseEntity<OcorrenciaResponseDTO> updateOcorrencia(@PathVariable Long id, @RequestBody OcorrenciaRequestDTO data) {
		Ocorrencia ocorrenciaExistente = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
		
		if(ocorrenciaExistente.getStatus() == StatusOcorrencia.FINALIZADA) {
			throw new RuntimeException("Esta ocorrência já foi finalizada, portanto não pode ser editada!");
		}
		ocorrenciaExistente.setData(data.data());
		ocorrenciaExistente.setStatus(data.status());
		ocorrenciaExistente.setCliente(data.cliente());
		ocorrenciaExistente.setEndereco(data.endereco());
        Ocorrencia updatedOcorrencia = repository.save(ocorrenciaExistente);
        return ResponseEntity.ok(new OcorrenciaResponseDTO(updatedOcorrencia));
	}
	
	@Operation(summary = "Deleta uma ocorrência", description = "Exclui uma ocorrência do sistema.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Ocorrência deletada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Ocorrência não encontrada")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOcorrencia(@PathVariable Long id) {
		Ocorrencia ocorrenciaExistente = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
		repository.delete(ocorrenciaExistente.getId());
        return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/finalizar/{id}")
	public ResponseEntity<OcorrenciaResponseDTO> finalizaOcorrencia(@PathVariable Long id){
		Ocorrencia ocorrencia = repository.findById(id).orElseThrow(() -> new RuntimeException("Ocorrência não encontrada"));
		ocorrencia.setStatus(StatusOcorrencia.FINALIZADA);
		repository.save(ocorrencia);
        return ResponseEntity.ok(new OcorrenciaResponseDTO(ocorrencia));
	

	}
}
