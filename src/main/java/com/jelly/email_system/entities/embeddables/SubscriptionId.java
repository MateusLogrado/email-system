package com.jelly.email_system.entities.embeddables;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SubscriptionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "id_empresa")
	private Long idEmpresa;
	
	public SubscriptionId() {}

	public SubscriptionId(Long idUsuario, Long idEmpresa) {
		super();
		this.idUsuario = idUsuario;
		this.idEmpresa = idEmpresa;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEmpresal() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEmpresa, idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubscriptionId other = (SubscriptionId) obj;
		return Objects.equals(idEmpresa, other.idEmpresa) && Objects.equals(idUsuario, other.idUsuario);
	}
	
	
	
}
