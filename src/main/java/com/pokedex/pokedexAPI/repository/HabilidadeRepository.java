package com.pokedex.pokedexAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pokedex.pokedexAPI.model.Habilidade;


public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
	
	public List<Habilidade> findAllByIdPokemon(Long idPokemon);

	public void deleteAllByIdPokemon(Long idPokemone);
	
	@Query("select descricao from Habilidade group by descricao order by count(*) desc")
	public List<String> findTopHabilities();

}