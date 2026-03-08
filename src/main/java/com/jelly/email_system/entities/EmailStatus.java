package com.jelly.email_system.entities;

import java.io.Serializable;

import com.jelly.email_system.entities.Enum.Status;
import com.jelly.email_system.entities.embeddables.EmailStatusId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "email_status")
public class EmailStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EmailStatusId id;
	
    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private User usuario;
    
    @ManyToOne
    @MapsId("idEmail")
    @JoinColumn(name = "id_email")
    private Email email;
    
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public EmailStatus() { }

	public EmailStatus(User usuario, Email email, Status status) {
		super();
		this.id = new EmailStatusId(usuario.getId(), email.getId());
		this.usuario = usuario;
		this.email = email;
		this.status = status;
	}

	public EmailStatusId getId() {
		return id;
	}

	public void setId(EmailStatusId id) {
		this.id = id;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
