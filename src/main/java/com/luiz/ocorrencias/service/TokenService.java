package com.luiz.ocorrencias.service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.luiz.ocorrencias.entity.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String chaveSecreta;
	
	public String generateToken(Usuario user) {
		try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
			 String token = JWT.create()
	                    .withIssuer("auth-api")
	                    .withSubject(user.getLogin())
					.withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro na geração do token: \n", e);
		}
	}
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(chaveSecreta);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }
}
