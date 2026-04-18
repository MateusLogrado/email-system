package com.jelly.email_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jelly.email_system.DTO.Request.CadastroRequestDTO;
import com.jelly.email_system.DTO.Request.LoginRequestDTO;
import com.jelly.email_system.DTO.Response.CadastroResponseDTO;
import com.jelly.email_system.DTO.Response.TokenResponseDTO;
import com.jelly.email_system.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/cadastro")
    public ResponseEntity<CadastroResponseDTO> cadastrar(@RequestBody CadastroRequestDTO user){
    	CadastroResponseDTO response = authService.criar(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
	
}
