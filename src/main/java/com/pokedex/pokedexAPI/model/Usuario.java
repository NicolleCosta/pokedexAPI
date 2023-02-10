package com.pokedex.pokedexAPI.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	private static final Long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@Column(name="usuario")
	private String usuario;
	
	@Column(name="senha")
	private String senha;

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Usuario(Long id, String usuario, String senha) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}