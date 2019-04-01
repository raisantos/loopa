package com.loopa.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cliente extends Usuario{
	
	@Id
	@GeneratedValue
	private Long id;
	private Long idUsuario;
	private double latitude;
	private double longitude;
	
	public Cliente() {
		super();
	}
	
	public Cliente(Long id, String email, String senha, String nome, String telefone, String endereco) {
		super(id, email, senha, nome, telefone, endereco);
	}
	
	public Cliente(Long id, Long idUsuario, double latitude, double longitude) {
		super();
		this.id = id;
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
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
