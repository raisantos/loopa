package com.loopa.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue
	private Long id;
	private int nota;
	private String comentario;
	private Long idProfissional;
	private Long idCliente;
	
	public Avaliacao() {
	}
	
	public Avaliacao(Long id, int nota, String comentario, Long idProfissional, Long idCliente) {
		super();
		this.id = id;
		this.nota = nota;
		this.comentario = comentario;
		this.idProfissional = idProfissional;
		this.idCliente = idCliente;
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
}
