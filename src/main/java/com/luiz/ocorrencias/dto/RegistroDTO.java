package com.luiz.ocorrencias.dto;

import com.luiz.ocorrencias.security.UserRole;

public record RegistroDTO(String username, String password, UserRole role) {

}
