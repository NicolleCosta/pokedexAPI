package com.pokedex.pokedexAPI.dto;

import java.io.Serializable;
import java.util.List;



public class PokemonDTO implements Serializable {

	private int id_pokemon;
	private String nome_pokemon;
    private int id_usuario;
    private String tipo_pokemon;
    private String foto_pokemon;
    private String habilidade_pokemon;
    
    
    
    public PokemonDTO() {
		super();
	}
    
    public PokemonDTO(int id_pokemon, String nome_pokemon, int id_usuario, String tipo_pokemon, String foto_pokemon,
			String habilidade_pokemon) {
		super();
		this.id_pokemon = id_pokemon;
		this.nome_pokemon = nome_pokemon;
		this.id_usuario = id_usuario;
		this.tipo_pokemon = tipo_pokemon;
		this.foto_pokemon = foto_pokemon;
		this.habilidade_pokemon = habilidade_pokemon;
	}
	
    public int getId_pokemon() {
		return id_pokemon;
	}
	public void setId_pokemon(int id_pokemon) {
		this.id_pokemon = id_pokemon;
	}
	public String getNome_pokemon() {
		return nome_pokemon;
	}
	public void setNome_pokemon(String nome_pokemon) {
		this.nome_pokemon = nome_pokemon;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getTipo_pokemon() {
		return tipo_pokemon;
	}
	public void setTipo_pokemon(String tipo_pokemon) {
		this.tipo_pokemon = tipo_pokemon;
	}
	public String getFoto_pokemon() {
		return foto_pokemon;
	}
	public void setFoto_pokemon(String foto_pokemon) {
		this.foto_pokemon = foto_pokemon;
	}
	public String getHabilidade_pokemon() {
		return habilidade_pokemon;
	}
	public void setHabilidade_pokemon(String habilidade_pokemon) {
		this.habilidade_pokemon = habilidade_pokemon;
	}
	

}
