package com.jelly.email_system.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "emails")
public class Email implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String assunto;
	
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String corpo;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime dataEnvio;
	
	@NotNull
	private boolean promocianal;
	
	@ManyToOne
	@JoinColumn(name = "idRemetente", nullable = false)
	private User remetente;
	
	@OneToMany(mappedBy = "email")
	private List<EmailStatus> emailstatus = new ArrayList<>();
        
        public Email() {}

	public Email(String assunto, String corpo, boolean promocianal, User remetente) {
		super();
		this.assunto = assunto;
		this.corpo = corpo;
		this.promocianal = promocianal;
                this.remetente = remetente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	public LocalDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	@PrePersist
	public void prePersist() {
	    this.dataEnvio = LocalDateTime.now();
	}

	public boolean isPromocianal() {
		return promocianal;
	}

	public void setPromocianal(boolean promocianal) {
		this.promocianal = promocianal;
	}
	

	public User getRemetente() {
		return remetente;
	}

	public void setRemetente(User remetente) {
		this.remetente = remetente;
	}

	public List<EmailStatus> getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(List<EmailStatus> emailstatus) {
		this.emailstatus = emailstatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
