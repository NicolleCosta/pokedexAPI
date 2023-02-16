package com.pokedex.pokedexAPI.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.pokedex.pokedexAPI.dto.PokemonDTO;
import com.pokedex.pokedexAPI.model.Pokemon;
import com.pokedex.pokedexAPI.repository.PokemonRepository;







@CrossOrigin
@RestController
public class PokemonREST {
	

	public static List<Pokemon> lista = new ArrayList<>();

	@Autowired
	private PokemonRepository PokemonRepo;


	@Autowired
	private ModelMapper mapper;

	@GetMapping("/Pokemons/count")
	public long contaPokemons() {
		return PokemonRepo.count();
	
	}
	
	@GetMapping("/habilidades/top")
	public List<String> topTipo() {
		return PokemonRepo.TopHabilidades();
	}
	
	@GetMapping("/tipos/top")
	public List<String> topTipoPokemon() {

		return PokemonRepo.findTopTipo();
	}

	/*
	@GetMapping("/Pokemons")
	public List<PokemonDTO> obterTodosPokemons() {
		List<Pokemon> lista = PokemonRepo.findByOrderByNomeAsc();
		// Converte lista de Entity para lista DTO
		return lista.stream()
				.map(e -> mapper.map(e, PokemonDTO.class))
				.collect(Collectors.toList());
	}

	@GetMapping("/Pokemons/{hab}/habilidades")
	public List<PokemonDTO> obterPokemonsPorHabilidade(@PathVariable("hab") String hab) {
		List<Pokemon> lista = PokemonRepo.findPokemonByHability(hab);
		System.out.println("Size:" + lista.size());
		// Converte lista de Entity para lista DTO
		return lista.stream()
				.map(e -> mapper.map(e, PokemonDTO.class))
				.collect(Collectors.toList());
	}
*/
	@GetMapping("/ProcurarTipo/{tipo}")
	public List<PokemonDTO> procurarTipo(@PathVariable("tipo") String tipo) {
		List<Pokemon> lista = PokemonRepo.findPokemonByTipo(tipo);
		System.out.println(tipo);
		System.out.println("Size:" + lista.size());
		return lista.stream()
				.map(e -> mapper.map(e, PokemonDTO.class))
				.collect(Collectors.toList());
	
	}

	@PostMapping("/Pokemons")
	public ResponseEntity<PokemonDTO> inserir(@RequestBody PokemonDTO PokemonDTO) {
		try {
			//mapeia o que veio da internet (DTO para uma classe dentro do nosso projeto)
			//Pokemon novo = mapper.map(PokemonDTO,Pokemon.class);
			Pokemon Pokemon = PokemonRepo.save(mapper.map(PokemonDTO, Pokemon.class));
			return ResponseEntity.ok().body(mapper.map(Pokemon, PokemonDTO.class));

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(409).build();
		}
	}
	
/*
	@PutMapping("/Pokemons/{id}")
	@Transactional 
	public ResponseEntity<PokemonDTO> alterar(@PathVariable("id") Long id, @RequestBody PokemonDTO PokemonDTO) {

		PokemonDTO.setId(id);
		
		try {
			Pokemon Pokemon = PokemonRepo.save(mapper.map(PokemonDTO, Pokemon.class));
	
			habilidadeRepo.deleteAllByIdPokemon(id);
			
						return ResponseEntity.ok().body(mapper.map(Pokemon, PokemonDTO.class));

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(409).build();
		}
	}

	@DeleteMapping("/Pokemons/{id}")
	@Transactional 
	public ResponseEntity<PokemonDTO> remover(@PathVariable("id") Long id) {

		habilidadeRepo.deleteAllByIdPokemon(id);

		PokemonRepo.deleteById(id);

		return ResponseEntity.ok().body(new PokemonDTO(id, null, null, null, null, null));

	}*/

}