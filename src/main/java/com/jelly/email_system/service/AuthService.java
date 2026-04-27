package com.jelly.email_system.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jelly.email_system.DTO.Request.CadastroRequestDTO;
import com.jelly.email_system.DTO.Request.LoginRequestDTO;
import com.jelly.email_system.DTO.Response.CadastroResponseDTO;
import com.jelly.email_system.DTO.Response.TokenResponseDTO;
import com.jelly.email_system.entities.User;
import com.jelly.email_system.entities.Enum.TipoUser;
import com.jelly.email_system.repository.UserRepository;
import com.jelly.email_system.security.JwtService;

@Service
public class AuthService implements UserDetailsService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, 
			JwtService jwtService, @Lazy AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}
	
	
	public CadastroResponseDTO criar(CadastroRequestDTO user) {		
		if(userRepository.existsByEmail(user.email())) {
			throw new RuntimeException("Esse email ja esta sendo usado!");
		}
		
		User novoUser = new User();
		novoUser.setNome(user.nome());
		novoUser.setEmail(user.email());
		// faz a criptografia na senha
		novoUser.setSenha(passwordEncoder.encode(user.senha()));
		novoUser.setTipo(TipoUser.NORMAL);
		
		User salvo = userRepository.save(novoUser);
		
		return new CadastroResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
	}
	
	public CadastroResponseDTO criarContaEmpresa(CadastroRequestDTO user) {		
		if(userRepository.existsByEmail(user.email())) {
			throw new RuntimeException("Esse email ja esta sendo usado!");
		}
		
		User novoUser = new User();
		novoUser.setNome(user.nome());
		novoUser.setEmail(user.email());
		novoUser.setSenha(passwordEncoder.encode(user.senha()));
		novoUser.setTipo(TipoUser.EMPRESA);
		
		User salvo = userRepository.save(novoUser);
		
		return new CadastroResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
	}
	
	public TokenResponseDTO login(LoginRequestDTO dto) {

	    // verifica email e senha
	    authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
	    );

	    // busca o usuário
	    User user = userRepository.findByEmail(dto.email())
	        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	    // gera o token
	    String token = jwtService.gerarToken(user);

	    return new TokenResponseDTO(token);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    return userRepository.findByEmail(username)
	        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
}
