package com.loopa.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.loopa.api.model.enums.*;

@Entity
@Table(name="cliente")
public class Cliente  extends Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue
	//private Long id;
	
	//@OneToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "id_usuario", nullable = false)
	//private Usuario usuario;
	
	private double latitude;
	private double longitude;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="perfis_cliente")
	private Set<Integer> perfis = new HashSet<>();
	
	public Cliente() {
		super();
	}
	
	public Cliente(Long id, String email, String senha, String nome, String telefone, String endereco) {
		super(id, email, senha, nome, telefone, endereco);
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	//public Long getId() {
	//	return id;
	//}
	//public void setId(Long id) {
	//	this.id = id;
	//}
	//public Usuario getUsuario() {
	//	return usuario;
	//}

	//public void setUsuario(Usuario usuario) {
	//	this.usuario = usuario;
	//}
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
