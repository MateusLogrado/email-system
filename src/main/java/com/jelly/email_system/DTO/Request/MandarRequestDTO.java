package com.jelly.email_system.DTO.Request;

import java.util.List;

public record MandarRequestDTO(
        String assunto,
        String corpo,
        List<String> destinatarios,
        boolean promocional
        ) {

}
