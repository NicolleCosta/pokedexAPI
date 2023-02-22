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

	
	@GetMapping("/Pokemons")
	public List<PokemonDTO> obterTodosPokemons() {
		List<Pokemon> lista = PokemonRepo.findByOrderByNomeAsc();
		
		List<PokemonDTO> convertido = new ArrayList<>();
		
		for(int i = 0; i<lista.size(); i++) {
			Pokemon pokaux = lista.get(i);
			PokemonDTO pok = pokaux.toPokemonDTO();
			convertido.add(pok);
		}

		return convertido;
	}

	@GetMapping("/ProcurarTipo/{tipo}")
	public List<String> procurarTipo(@PathVariable("tipo") String tipo) {
		
		return PokemonRepo.findPokemonByTipo(tipo);
	
	}
	
	@GetMapping("/pokemon/{id}")
	public PokemonDTO getPokemonById(@PathVariable("id") int id){
		
		Pokemon pokemon = PokemonRepo.getPokemonById(id);
		PokemonDTO pokemonDTO = pokemon.toPokemonDTO();
		
		System.out.println(pokemonDTO.getNome_pokemon());
		
		return pokemonDTO;
	}
	
	@GetMapping("/ProcurarHabilidade/{habilidade}")
	public List<String> procurarHabilidade(@PathVariable("habilidade") String habilidade) {
		
		return PokemonRepo.findPokemonByHabilidade(habilidade);
	
	}

	@PostMapping("/PokemonCadastro")
	public ResponseEntity<PokemonDTO> inserir(@RequestBody PokemonDTO PokemonDTO) {
		try {
			//mapeia o que veio da internet (DTO para uma classe dentro do nosso projeto)
			//Pokemon novo = mapper.map(PokemonDTO,Pokemon.class);
			Pokemon pokeconsulta = mapper.map(PokemonDTO, Pokemon.class);
			Pokemon aux = PokemonRepo.findByNome(pokeconsulta.getNome());
			if (pokeconsulta.getNome().equals(aux.getNome())) {
				return ResponseEntity.status(409).build();
			}
			
			
			
			Pokemon Pokemon = PokemonRepo.save(mapper.map(PokemonDTO, Pokemon.class));
			return ResponseEntity.ok().body(mapper.map(Pokemon, PokemonDTO.class));

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(409).build();
		}
	}
	

	@PutMapping("/PokemonUpdate/{id}")
	@Transactional 
	public ResponseEntity<PokemonDTO> alterar(@PathVariable("id") int id, @RequestBody PokemonDTO PokemonDTO) {
		System.out.println(PokemonDTO.getNome_pokemon());
		System.out.println(PokemonDTO.getHabilidade_pokemon());
		System.out.println(PokemonDTO.getTipo_pokemon());
		System.out.println(PokemonDTO.getId_pokemon());
		System.out.println();
		
		
		
		
		PokemonDTO.setId_usuario(id);
		Pokemon pokeAux = PokemonDTO.toPokemon();
		System.out.println(pokeAux.getNome() + "nome pokeaux");
		
		Pokemon pokeNome = PokemonRepo.getPokemonById(pokeAux.getI_pokemon());
		System.out.println(pokeNome.getNome() + "nome pokenome");
		
		try {
		//	Pokemon Pokemon = PokemonRepo.save(mapper.map(PokemonDTO, Pokemon.class));
		/*	
			if (pokeAux.getNome().equals(pokeNome.getNome())) {
				return ResponseEntity.status(409).build();
			}
			*/
			PokemonRepo.save(pokeAux);
						PokemonDTO = pokeAux.toPokemonDTO();
						return ResponseEntity.ok().body(PokemonDTO);

		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(409).build();
		}
	}
 
	
	@DeleteMapping("/DeletePokemon/{id_pokemon}")
	@Transactional 
	public ResponseEntity<PokemonDTO> delete(@PathVariable("id_pokemon") int id_pokemon) {
		
		PokemonRepo.deletaPokemon(id_pokemon);
		
		return ResponseEntity.ok().body(null);
				
			
	}
	
	

}