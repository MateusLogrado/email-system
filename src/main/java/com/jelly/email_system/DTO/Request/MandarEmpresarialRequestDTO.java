package com.jelly.email_system.DTO.Request;


public record MandarEmpresarialRequestDTO(
        String assunto,
        String corpo,
        int emailId,
        boolean promocional
		) {

}
