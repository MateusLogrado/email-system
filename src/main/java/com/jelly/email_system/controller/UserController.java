package com.jelly.email_system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jelly.email_system.DTO.Request.EmailRequestDTO;
import com.jelly.email_system.DTO.Request.SubscriptionRequestDTO;
import com.jelly.email_system.DTO.Response.InscreverResponseDTO;
import com.jelly.email_system.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/inscricao")
    public ResponseEntity<String> inscricao(@RequestBody SubscriptionRequestDTO inscricao){
    	String response = userService.subscription(inscricao);
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PostMapping("/listar")
    public ResponseEntity<List<InscreverResponseDTO>> listar(@RequestBody EmailRequestDTO emailRequest) {
        List<InscreverResponseDTO> resultado = userService.listarSubscription(emailRequest);
        return ResponseEntity.ok(resultado);
    }

}
