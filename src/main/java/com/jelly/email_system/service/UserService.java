package com.jelly.email_system.service;

import org.springframework.stereotype.Service;

import com.jelly.email_system.entities.User;
import com.jelly.email_system.repository.UserReposiotry;

@Service
public class UserService {
	
	private final UserReposiotry userRepository;

	public UserService(UserReposiotry userRepository) {
		this.userRepository = userRepository;
	}
	
	public User criar(User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Esse email ja esta sendo usado!");
		}
		return userRepository.save(user);
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
	
	
}
