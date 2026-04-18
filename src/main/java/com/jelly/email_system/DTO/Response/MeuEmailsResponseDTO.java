package com.jelly.email_system.DTO.Response;

import com.jelly.email_system.entities.Enum.Status;
import com.jelly.email_system.entities.embeddables.EmailStatusId;

import java.time.LocalDateTime;

public record MeuEmailsResponseDTO(
        String assunto,
        String corpo,
        String remetente,
        Status status,
        EmailStatusId idStatus,
        LocalDateTime dataEnvio) {

}
