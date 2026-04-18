package com.jelly.email_system.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    
    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        // 1. pega o header Authorization
        String header = request.getHeader("Authorization");

        // 2. se não tem token, deixa passar
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // 3. extrai o token do header
        String token = header.substring(7); // remove o "Bearer "

        // 4. extrai o email do token
        String email = jwtService.extrairEmail(token);

        // 5. se tem email e não está autenticado ainda
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. busca o usuário no banco
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // 7. valida o token
            if (jwtService.isValido(token, userDetails)) {

                // 8. autentica o usuário no contexto do Spring Security
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 9. deixa a requisição continuar
        chain.doFilter(request, response);
    }
	
}
