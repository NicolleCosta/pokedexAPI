package com.pokedex.pokedexAPI.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{

	private String usuario;
	private String senha;
	
	public LoginDTO() {
		super();
	}
	
	public LoginDTO(String usuario, String senha) {
		super();
		this.usuario = usuario;
		this.senha = senha;
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
