package com.loopa.api.model;

import java.io.Serializable;

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
@Table(name="avaliacao")
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int nota;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name = "id_profissional", nullable = false)
	private Profissional profissional;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;
	
	public Avaliacao() {
	}
	
	public Avaliacao(Long id, int nota, String comentario, Profissional profissional, Cliente cliente) {
		super();
		this.id = id;
		this.nota = nota;
		this.comentario = comentario;
		this.profissional = profissional;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getNota() {
		return nota;
	}
	
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
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
}
