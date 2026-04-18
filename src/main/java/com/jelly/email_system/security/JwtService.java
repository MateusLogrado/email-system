package com.jelly.email_system.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret}") // pega a chave que esta no application.properties
	private String secretKey;
	
	public String gerarToken(UserDetails userDetails) {
		return Jwts.builder() // inicia a construção do token
				.subject(userDetails.getUsername()) // define o "dono" do token - o email do usuário
				.issuedAt(new Date()) // data/hora que o token foi criado - agora
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // quando expira
				.signWith(getSecretKey()) // assina o token com a chave secreta
				.compact(); // gera a String final do token
	}
	
	public String extrairEmail(String token) { // extrair o email do token e retorna o email
		return extrairClaim(token, Claims::getSubject);
	}
	
	public boolean isValido(String token, UserDetails userDetails) { // verifica se o token é valido
		String email = extrairEmail(token);
		return email.equals(userDetails.getUsername()) && !isExpirado(token);
	}
	
	private boolean isExpirado(String token) { // retorna false quando não expira e true para quando expira
		return extrairExpiracao(token).before(new Date());
	}
	
    private Date extrairExpiracao(String token) { // extrai a data de expiração dentro do token
        return extrairClaim(token, Claims::getExpiration);
    }
    
    private <T> T extrairClaim(String token, Function<Claims, T> resolver) { //Abre o token, verifica se é legítimo e extrai a informação pedida
        Claims claims = Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
        return resolver.apply(claims);
    }
    
    private SecretKey getSecretKey() { // transforma a chave secreta em algo que o jwt entenda para assinar o token
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
	
}
