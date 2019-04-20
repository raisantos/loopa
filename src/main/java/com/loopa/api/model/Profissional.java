package com.loopa.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.loopa.api.model.enums.*;

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
	
	private String status;

	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="perfis_profissional")
	private Set<Integer> perfis = new HashSet<>();
	
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

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
