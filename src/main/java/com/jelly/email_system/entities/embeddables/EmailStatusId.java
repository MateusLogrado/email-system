package com.jelly.email_system.entities.embeddables;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmailStatusId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "id_email")
	private Long idEmail;

	public EmailStatusId(Long idUsuario, Long idEmail) {
		super();
		this.idUsuario = idUsuario;
		this.idEmail = idEmail;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEmail, idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmailStatusId other = (EmailStatusId) obj;
		return Objects.equals(idEmail, other.idEmail) && Objects.equals(idUsuario, other.idUsuario);
	}
	
	
	
}
