package com.pokedex.pokedexAPI.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pokemon")
public class Pokemon implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pokemon")
	private Long id;
	
	@Column(name="nome_pokemon")
	private String nome;
	
	@Column(name="id_usuario")
	private Long idUsuario;
	
	@Column(name="tipo_pokemon")
	private String tipo;

	@Column(name="foto_pokemon")
	private String foto;

	public Pokemon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pokemon(Long id, String nome, Long idUsuario, String tipo, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.idUsuario = idUsuario;
		this.tipo = tipo;
		this.foto = foto;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto(String foto) {
		this.foto = foto;
	}

}
	