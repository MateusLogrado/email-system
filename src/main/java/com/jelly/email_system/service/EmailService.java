
package com.jelly.email_system.service;

import com.jelly.email_system.entities.Email;
import com.jelly.email_system.repository.EmailRepository;
import com.jelly.email_system.DTO.Request.MandarRequestDTO;
import com.jelly.email_system.DTO.Response.MeuEmailsResponseDTO;
import com.jelly.email_system.entities.EmailStatus;
import com.jelly.email_system.entities.Enum.Status;
import com.jelly.email_system.entities.User;
import com.jelly.email_system.repository.EmailStatusRepository;

import com.jelly.email_system.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    private final EmailRepository emailRepository;
    private final UserRepository userRepository;
    private final EmailStatusRepository emailStatusRepository;
    
    public EmailService(EmailRepository emailRepository, UserRepository userRepository, EmailStatusRepository emailStatusRepository){
        this.emailRepository = emailRepository;
        this.userRepository = userRepository;
        this.emailStatusRepository = emailStatusRepository;
    }
    
    public String mandar(MandarRequestDTO emailDTO){
        
        String emailRemetente = SecurityContextHolder.getContext()
            .getAuthentication()
            .getName();
        
        User remetente = userRepository.findByEmail(emailRemetente)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
        List<User> destinatarios = userRepository.findAllByEmailIn(emailDTO.destinatarios());
               
        if (destinatarios.isEmpty()) {
            throw new RuntimeException("Nenhum destinatário encontrado");
        }
        
        Email email = new Email(emailDTO.assunto(), emailDTO.corpo(), emailDTO.promocional(), remetente);
        emailRepository.save(email);
        
        for (User dest : destinatarios) {
                EmailStatus status = new EmailStatus(dest, email, Status.NAO_LIDO);
                   emailStatusRepository.save(status);
        }
        
        return "Email enviado com sucesso!";
        
    }
    
    public List<MeuEmailsResponseDTO> meusEmails(String email){
        if(!userRepository.existsByEmail(email)){
            throw new RuntimeException("Usuário não encontrado");
        }
        List<MeuEmailsResponseDTO> meusEmails = new ArrayList<>();
        List<EmailStatus> listaStatus = emailStatusRepository.findAll();
        
        for(EmailStatus status: listaStatus){
            User user = status.getUsuario();
                        
            if(email.equals(user.getEmail())){
                Email emailV = status.getEmail();
                MeuEmailsResponseDTO meuEmail = new MeuEmailsResponseDTO(
                emailV.getAssunto(),
                emailV.getCorpo(),
                emailV.getRemetente().getEmail(),
                status.getStatus(),
                emailV.getDataEnvio()
                );
                
                meusEmails.add(meuEmail);
                
                }
        }
        
        return meusEmails;
    }
    
}
