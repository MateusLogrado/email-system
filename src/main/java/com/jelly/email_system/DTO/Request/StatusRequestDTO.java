package com.jelly.email_system.DTO.Request;

import com.jelly.email_system.DTO.IdStatusDTO;

public record StatusRequestDTO(
		String statusEmail,
		IdStatusDTO idStatus
		) {

}
