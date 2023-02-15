package com.pokedex.pokedexAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pokedex.pokedexAPI.model.Pokemon;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	
	/*@Query("SELECT DISTINCT p FROM Pokemon p "
			+ "INNER JOIN Habilidade h ON p.id = h.idPokemon "
			+ "WHERE UPPER(h.descricao) "
			+ "LIKE UPPER(CONCAT('%',:hab,'%'))")
	public List<Pokemon> findPokemonByHability(@Param("hab") String hab);

	public List<Pokemon> findByOrderByNomeAsc();
*/
	
	
	@Query("select habilidade from Pokemon group by habilidade_pokemon order by count(*) desc")
	public List<String> TopHabilidades();

	
	
	
	@Query("select tipo from Pokemon group by tipo order by count(*) desc")
	public List<String> findTopTipo();



	@Query("select nome from Pokemon where tipo_pokemon like ('%',:tipo,'%')")
	public List<String> procurarTipo(@Param("tipo")String tipo);
	
	/*@Query ("select tipo_pokemon from pokemon group by tipo_pokemon order by count(*) desc")
	public List<String> findTopTipo();*/

}


