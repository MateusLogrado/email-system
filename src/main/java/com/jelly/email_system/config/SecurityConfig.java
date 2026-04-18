package com.jelly.email_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jelly.email_system.security.JwtFilter;

@Configuration // marca a classe como fonte de configurações
@EnableWebSecurity // ativa o spring Security
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;
	
    public SecurityConfig(@Lazy JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

	@Bean // registra um objeto no contexto do Spring pra injeção
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception {
        return config.getAuthenticationManager();
    }
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // sem sessão
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // rotas públicas
                .anyRequest().authenticated()            // resto precisa de token
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // registra o filtro

        return http.build();
    }
	
}
