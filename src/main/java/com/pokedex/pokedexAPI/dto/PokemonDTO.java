package com.pokedex.pokedexAPI.dto;

import java.io.Serializable;
import java.util.List;


public class PokemonDTO implements Serializable {

	private Long id;
	private String nome;
	private Long idUsuario;
	private List<HabilidadeDTO> habilidades;
	private String tipo;
	private String foto;
	
	public PokemonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PokemonDTO(Long id, String nome, Long idUsuario, List<HabilidadeDTO> habilidades, String tipo, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.idUsuario = idUsuario;
		this.habilidades = habilidades;
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



	public List<HabilidadeDTO> getHabilidades() {
		return habilidades;
	}



	public void setHabilidades(List<HabilidadeDTO> habilidades) {
		this.habilidades = habilidades;
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
