package com.jelly.email_system.controller;

import com.jelly.email_system.DTO.Request.MandarRequestDTO;
import com.jelly.email_system.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Email")
public class EmailController {
    
    private final EmailService emailService;
    
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    
    @PostMapping("/mandar")
    public ResponseEntity<String> mandar(@RequestBody MandarRequestDTO emailDTO) {
        String response = emailService.mandar(emailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
