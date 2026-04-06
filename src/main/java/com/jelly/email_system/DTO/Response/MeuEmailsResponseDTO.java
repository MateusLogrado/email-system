
package com.jelly.email_system.DTO.Response;

import com.jelly.email_system.entities.Enum.Status;
import java.time.LocalDateTime;

public record MeuEmailsResponseDTO(
        String assunto,
        String corpo,
        String remetente,
        Status status,
        LocalDateTime dataEnvio) {

}
