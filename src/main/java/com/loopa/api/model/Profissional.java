package com.loopa.api.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="profissional")
public class Profissional extends Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue
	//private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_servico", nullable = false)
	private Servico servico;
	
	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "id_usuario", nullable = false)
	//private Usuario usuario;
	
	private Double latitude;
	
	private Double longitude;
	
	public Profissional() {
		super();
	}

	public Profissional(Long id, Servico servico, Usuario usuario, Double latitude, Double longitude) {
		super();
		//this.id = id;
		this.servico = servico;
		//this.usuario = usuario;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Profissional(Long id, String email, String senha, String nome, String telefone, String endereco) {
		super(id, email, senha, nome, telefone, endereco);
	}

	//public Long getId() {
		//return id;
	//}

	//public void setId(Long id) {
		//this.id = id;
	//}
	
	public Servico getServico() {
		return servico;
	}
	
	public void setServico(Servico servico) {
		this.servico = servico;
	}

	//public Usuario getUsuario() {
		//return usuario;
	//}

	//public void setUsuario(Usuario usuario) {
		//this.usuario = usuario;
	//}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}		
}
