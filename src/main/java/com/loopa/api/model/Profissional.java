package com.loopa.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profissional extends Usuario{
	@Id
	@GeneratedValue
	private Long id;
	private Long idServico;
	private Long idUsuario;
	private Double latitude;
	private Double longitude;
	
	public Profissional() {
		super();
	}
	
	public Profissional(Long id, String email, String senha, String nome, String telefone, String endereco) {
		super(id, email, senha, nome, telefone, endereco);
	}
	
	public Profissional(Long id, Long idServico, Long idUsuario, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.idServico = idServico;
		this.idUsuario = idUsuario;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdServico() {
		return idServico;
	}
	
	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

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
