package com.pokedex.pokedexAPI.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="habilidade")
public class Habilidade implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="id_habilidade")
	private Long id;
	
	@Column(name="descricao_habilidade")
	private String descricao;

	@Column(name="id_pokemon")
	private Long idPokemon;

	public Habilidade() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Habilidade(Long id, String descricao, Long idPokemon) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.idPokemon = idPokemon;
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