package com.jelly.email_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jelly.email_system.DTO.Request.EmailRequestDTO;
import com.jelly.email_system.DTO.Request.SubscriptionRequestDTO;
import com.jelly.email_system.DTO.Response.InscreverResponseDTO;
import com.jelly.email_system.entities.Subscription;
import com.jelly.email_system.entities.User;
import com.jelly.email_system.entities.Enum.TipoUser;
import com.jelly.email_system.repository.SubscriptionRepository;
import com.jelly.email_system.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final SubscriptionRepository subscriptionRepository;

	public UserService(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
		this.userRepository = userRepository;
		this.subscriptionRepository = subscriptionRepository;
	}
	

	
	public User atualizarNome(Long id, User novo) {
		User usuario = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		usuario.setNome(novo.getNome());
		
		return userRepository.save(usuario);
	}
	
	public User atualizarSenha(Long id, User novo) {
		User usuario = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		usuario.setSenha(novo.getSenha());
		
		return userRepository.save(usuario);
	}
	
	public void deletar(Long id) {
		userRepository.deleteById(id);
	}
	
	public String subscription(SubscriptionRequestDTO incricao) {
		
	    User usuario = userRepository.findById(incricao.idUsuario())
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	        User empresa = userRepository.findById(incricao.idEmpresa())
	            .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

	        Subscription novo = new Subscription(usuario, empresa);

	        subscriptionRepository.save(novo);

	        return "Sucesso na inscrição";
		
	}
	
	public List<InscreverResponseDTO> listarSubscription(EmailRequestDTO email){
		
		List<User> empresas = userRepository.findAll();
		List<InscreverResponseDTO> inscricoesAcuradas = new ArrayList<>();
		User usuario = userRepository.findByEmail(email.email())
			.orElseThrow(() -> new RuntimeException("Usuario não encontrado com email: " + email.email()));
		
		
		
		for(User empresa : empresas) {
			
			if( empresa.getTipo() == TipoUser.EMPRESA ) {
				Optional<Subscription> ins = subscriptionRepository.findByEmail(email.email());
				
				InscreverResponseDTO inscricao;
				
				if(ins.isPresent()) {
					inscricao = new InscreverResponseDTO(true, empresa.getId(), empresa.getNome());
				}else{
					inscricao = new InscreverResponseDTO(false, empresa.getId(), empresa.getNome());
				}
				
				inscricoesAcuradas.add(inscricao);
			}
			
		}
		
		return inscricoesAcuradas;
	}
	
}
