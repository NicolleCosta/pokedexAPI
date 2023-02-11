package com.pokedex.pokedexAPI.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.pokedexAPI.dto.HabilidadeDTO;
import com.pokedex.pokedexAPI.dto.PokemonDTO;
import com.pokedex.pokedexAPI.model.Habilidade;
import com.pokedex.pokedexAPI.model.Pokemon;
import com.pokedex.pokedexAPI.repository.HabilidadeRepository;
import com.pokedex.pokedexAPI.repository.PokemonRepository;





@CrossOrigin
@RestController
public class PokemonREST {
	

	public static List<Pokemon> lista = new ArrayList<>();

	@Autowired
	private PokemonRepository PokemonRepo;

	@Autowired
	private HabilidadeRepository habilidadeRepo;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/Pokemons/count")
	public long contaPokemons() {

		return PokemonRepo.count();
	
	}

	@GetMapping("/habilidades/top")
	public List<String> topHabilidades() {

		return habilidadeRepo.findTopHabilities();
	
	}

	
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

	@GetMapping("/Pokemons/{id}")
	public ResponseEntity<PokemonDTO> obterPokemonPorId(@PathVariable("id") Long id) {
	
		PokemonDTO PokemonDTO = mapper.map(PokemonRepo.findById(id), PokemonDTO.class);
		
		if (PokemonDTO != null) {
			
			List<Habilidade> listaHabilidade = habilidadeRepo.findAllByIdPokemon(id);
			
			List<HabilidadeDTO> listaHabilidadeDTO = listaHabilidade.stream()
					.map(e -> mapper.map(e, HabilidadeDTO.class))
					.collect(Collectors.toList());	
			
			PokemonDTO.setHabilidades(listaHabilidadeDTO);
			
			return ResponseEntity.ok().body(PokemonDTO);

		} else 
			return ResponseEntity.status(404).build();
			
	}

	@PostMapping("/Pokemons")
	public ResponseEntity<PokemonDTO> inserir(@RequestBody PokemonDTO PokemonDTO) {

		try {
			Pokemon Pokemon = PokemonRepo.save(mapper.map(PokemonDTO, Pokemon.class));
	
			for (HabilidadeDTO habilidadeDTO : PokemonDTO.getHabilidades() ) {
				habilidadeDTO.setIdPokemon(Pokemon.getId());
				habilidadeRepo.save(mapper.map(habilidadeDTO, Habilidade.class));
			}
			
			return ResponseEntity.ok().body(mapper.map(Pokemon, PokemonDTO.class));

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(409).build();
		}
	}
	

	@PutMapping("/Pokemons/{id}")
	@Transactional 
	public ResponseEntity<PokemonDTO> alterar(@PathVariable("id") Long id, @RequestBody PokemonDTO PokemonDTO) {

		PokemonDTO.setId(id);
		
		try {
			Pokemon Pokemon = PokemonRepo.save(mapper.map(PokemonDTO, Pokemon.class));
	
			habilidadeRepo.deleteAllByIdPokemon(id);
			
			for (HabilidadeDTO habilidadeDTO : PokemonDTO.getHabilidades() ) {
				habilidadeDTO.setIdPokemon(Pokemon.getId());
				habilidadeRepo.save(mapper.map(habilidadeDTO, Habilidade.class));
			}
			
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

	}

}