package com.jelly.email_system.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.jelly.email_system.entities.embeddables.SubscriptionId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity
@Table(name = "subscricoes")
public class Subscription implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SubscriptionId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario")
    private User usuario;
    
    @ManyToOne
    @MapsId("idEmpresa")
    @JoinColumn(name = "id_empresa")
    private User empresa;
    
	@Column(nullable = false, updatable = false)
    private LocalDateTime dataInscricao;
	
	public Subscription() { }

	public Subscription(User usuario, User empresa, LocalDateTime dataInscricao) {
		super();
		this.id = new SubscriptionId(usuario.getId(), empresa.getId());
		this.usuario = usuario;
		this.empresa = empresa;
		this.dataInscricao = dataInscricao;
	}

	public SubscriptionId getId() {
		return id;
	}

	public void setId(SubscriptionId id) {
		this.id = id;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public User getEmpresa() {
		return empresa;
	}

	public void setEmpresa(User empresa) {
		this.empresa = empresa;
	}

	public LocalDateTime getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(LocalDateTime dataInscricao) {
		this.dataInscricao = dataInscricao;
	}
	
	@PrePersist
	public void prePersist() {
	    this.dataInscricao = LocalDateTime.now();
	}

}
