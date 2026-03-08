package com.jelly.email_system.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.jelly.email_system.entities.Enum.TipoUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usuarios")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull 
	private String nome;
	
	@NotNull 
	private String email;
	
	@Column(length = 255)
	@NotNull
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private TipoUser tipo;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime criadoEm;
	
    @OneToMany(mappedBy = "remetente")
    private List<Email> emailsEnviados = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<EmailStatus> emailStatus = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Subscription> inscricoes = new ArrayList<>();

    @OneToMany(mappedBy = "empresa")
    private List<Subscription> inscritos = new ArrayList<>();
	
	public User() { }

	public User(Long id, String nome, String email, String senha, TipoUser tipo, LocalDateTime criadoEm) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
		this.criadoEm = criadoEm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUser getTipo() {
		return tipo;
	}

	public void setTipo(TipoUser tipo) {
		this.tipo = tipo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	
	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	@PrePersist
	public void prePersist() {
	    this.criadoEm = LocalDateTime.now();
	}

	public List<Email> getEmailsEnviados() {
		return emailsEnviados;
	}

	public void setEmailsEnviados(List<Email> emailsEnviados) {
		this.emailsEnviados = emailsEnviados;
	}

	public List<EmailStatus> getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(List<EmailStatus> emailStatus) {
		this.emailStatus = emailStatus;
	}

	public List<Subscription> getInscricoes() {
		return inscricoes;
	}

	public void setInscricoes(List<Subscription> inscricoes) {
		this.inscricoes = inscricoes;
	}

	public List<Subscription> getInscritos() {
		return inscritos;
	}

	public void setInscritos(List<Subscription> inscritos) {
		this.inscritos = inscritos;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}
