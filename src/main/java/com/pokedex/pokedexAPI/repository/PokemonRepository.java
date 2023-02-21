package com.pokedex.pokedexAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pokedex.pokedexAPI.model.Pokemon;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

	public List<Pokemon> findByOrderByNomeAsc();

	@Query("select habilidade from Pokemon group by habilidade_pokemon order by count(*) desc")
	public List<String> TopHabilidades();

	
	@Query("select tipo from Pokemon group by tipo order by count(*) desc")
	public List<String> findTopTipo();


	@Query("SELECT nome FROM Pokemon p WHERE UPPER(p.tipo) LIKE UPPER(CONCAT('%', :tipo, '%'))")
    public List<String> findPokemonByTipo(@Param("tipo") String tipo);


	@Query("SELECT nome FROM Pokemon p WHERE UPPER(p.habilidade) LIKE UPPER(CONCAT('%', :habilidade, '%'))")
	public List<String> findPokemonByHabilidade(String habilidade);
	
	
	@Query("FROM Pokemon WHERE id_pokemon = :id")
	public Pokemon getPokemonById (@Param("id") int id);
	
	@Modifying
	@Query("DELETE FROM Pokemon WHERE id_pokemon = :id_pokemon")
	public void deletaPokemon(int id_pokemon);

	public Pokemon findByNome(String nome);
	

}


