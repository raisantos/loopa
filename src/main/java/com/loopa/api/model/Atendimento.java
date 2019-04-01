package com.loopa.api.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Atendimento {

	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	private Long idProfissional;
	private Long idCliente;
	private double latitudeCliente;
	private double longitudeCliente;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Long getIdProfissional() {
		return idProfissional;
	}
	
	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public double getLatitudeCliente() {
		return latitudeCliente;
	}
	
	public void setLatitudeCliente(double latitudeCliente) {
		this.latitudeCliente = latitudeCliente;
	}
	
	public double getLongitudeCliente() {
		return longitudeCliente;
	}
	
	public void setLongitudeCliente(double longitudeCliente) {
		this.longitudeCliente = longitudeCliente;
	}
}
