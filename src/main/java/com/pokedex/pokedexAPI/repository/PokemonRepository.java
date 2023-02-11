package com.pokedex.pokedexAPI.repository;

import java.util.List;


import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.pokedex.pokedexAPI.model.Pokemon;



public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	
	@Query("SELECT DISTINCT p FROM Pokemon p "
			+ "INNER JOIN Habilidade h ON p.id = h.idPokemon "
			+ "WHERE UPPER(h.descricao) "
			+ "LIKE UPPER(CONCAT('%',:hab,'%'))")
	public List<Pokemon> findPokemonByHability(@Param("hab") String hab);

	public List<Pokemon> findByOrderByNomeAsc();

}


