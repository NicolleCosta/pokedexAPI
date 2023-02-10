package com.pokedex.pokedexAPI.dto;
import java.io.Serializable;

public class HabilidadeDTO implements Serializable{

	private Long id;
	private String descricao;
	private Long idPokemon;
	
	public HabilidadeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HabilidadeDTO(Long id, String descricao, Long idPokemon) {
		super();
		this.idPokemon = idPokemon;
		this.id = id;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdPokemon() {
		return idPokemon;
	}

	public void setIdPokemon(Long idPokemon) {
		this.idPokemon = idPokemon;
	}
	
	
}