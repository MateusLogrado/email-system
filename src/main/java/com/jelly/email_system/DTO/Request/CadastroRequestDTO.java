package com.jelly.email_system.DTO.Request;

import com.jelly.email_system.entities.Enum.TipoUser;

public record CadastroRequestDTO(
		String nome,
		String email,
		String senha,
		TipoUser tipo
		) {
	
}
