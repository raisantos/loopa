package com.loopa.api.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="atendimento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Atendimento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date data;
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_profissional", nullable = false)
	private Profissional profissional;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	private double latitudeCliente;
	private double longitudeCliente;
	
	private String status;

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
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Profissional getProfissional() {
		return profissional;
	}
	
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
