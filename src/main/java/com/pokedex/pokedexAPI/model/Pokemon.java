package com.pokedex.pokedexAPI.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.pokedex.pokedexAPI.dto.PokemonDTO;


@Entity
@Table(name="pokemon")
public class Pokemon implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pokemon")
	private int id_pokemon;
	
	@Column(name="nome_pokemon")
	private String nome;
	
	@Column(name="id_usuario")
	private int id_usuario;
	
	@Column(name="tipo_pokemon")
	private String tipo;

	@Column(name="foto_pokemon")
	private String foto;
	
	@Column(name="habilidade_pokemon")
	private String habilidade;

	public Pokemon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Pokemon(int id, String nome, int id_Usuario, String tipo, String foto, String habilidade) {
		super();
		this.id_pokemon = id;
		this.nome = nome;
		this.id_usuario = id_Usuario;
		this.tipo = tipo;
		this.foto = foto;
		this.habilidade = habilidade;
	}



	public String getHabilidade() {
		return habilidade;
	}

	public void setHabilidade_pokemon(String habilidade) {
		this.habilidade = habilidade;
	}

	public int getI_pokemon() {
		return id_pokemon;
	}



	public void setId_pokemon(int id_pokemon) {
		this.id_pokemon = id_pokemon;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public int getIdUsuario() {
		return id_usuario;
	}



	public void setId_usuario(int idUsuario) {
		this.id_usuario = idUsuario;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo_pokemon(String tipo) {
		this.tipo = tipo;
	}



	public String getFoto() {
		return foto;
	}



	public void setFoto_pokemon(String foto) {
		this.foto = foto;
	}
	
	public PokemonDTO toPokemonDTO() {
        ModelMapper modelMapper = new ModelMapper();
        PokemonDTO pokemonDTO = modelMapper.map(this, PokemonDTO.class);
        pokemonDTO.setId_usuario(this.id_usuario);
        pokemonDTO.setId_pokemon(this.id_pokemon);
        pokemonDTO.setNome_pokemon(this.nome);
        pokemonDTO.setTipo_pokemon(this.tipo);
        pokemonDTO.setFoto_pokemon(this.foto);
        pokemonDTO.setHabilidade_pokemon(this.habilidade);
        return pokemonDTO;
    }
	
	

	
	
	
	

}
	