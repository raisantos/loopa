package com.loopa.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Avaliacao {

	@Id
	@GeneratedValue
	private int id;
	private String comentario;
	private int nota;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + id;
		result = prime * result + nota;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avaliacao other = (Avaliacao) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (id != other.id)
			return false;
		if (nota != other.nota)
			return false;
		return true;
	}
}
