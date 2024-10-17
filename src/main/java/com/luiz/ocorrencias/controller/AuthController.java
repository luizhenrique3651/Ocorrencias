package com.luiz.ocorrencias.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luiz.ocorrencias.dto.JwtResponseDTO;
import com.luiz.ocorrencias.dto.LoginRequestDTO;
import com.luiz.ocorrencias.dto.RegistroDTO;
import com.luiz.ocorrencias.entity.Usuario;
import com.luiz.ocorrencias.repository.UsuarioRepository;
import com.luiz.ocorrencias.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;
    
    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private TokenService tokenService;

    @Operation(summary = "Realiza login de um usuário", description = "Autentica o usuário e retorna um token JWT.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new JwtResponseDTO(token));
    }

    @Operation(summary = "Registra um novo usuário", description = "Cadastra um novo usuário no sistema com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Usuário já registrado")
    })
    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Validated RegistroDTO data) {
        if (this.repository.findByLogin(data.username()) != null) {
            return ResponseEntity.badRequest().body("Usuario já registrado no sistema.");
        }
        String senhaEncriptada = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUsuario = new Usuario(data.username(), senhaEncriptada, data.role());

        this.repository.save(newUsuario);
        return ResponseEntity.ok("Usuario cadastrado com sucesso!.");
    }
}
