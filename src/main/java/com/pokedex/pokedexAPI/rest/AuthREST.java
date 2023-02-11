package com.pokedex.pokedexAPI.rest;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import com.pokedex.pokedexAPI.dto.LoginDTO;
import com.pokedex.pokedexAPI.dto.UsuarioDTO;
import com.pokedex.pokedexAPI.model.Usuario;
import com.pokedex.pokedexAPI.repository.UsuarioRepository;


@CrossOrigin
@RestController
public class AuthREST {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private ModelMapper mapper;
	

	
	@PostMapping("/login")
	ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO login) {
		
		Usuario usuario = repo.findByLoginAndSenha(login.getLogin(), login.getSenha());
		
		if (usuario != null) {
			return ResponseEntity.ok().body(mapper.map(usuario, UsuarioDTO.class));
		} else {
			return ResponseEntity.status(401).build();
		}
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<UsuarioDTO> obterUsuarioPorId(@PathVariable("id") Long id) {
	
		UsuarioDTO usuarioDTO = mapper.map(repo.findById(id), UsuarioDTO.class);
		
		if (usuarioDTO != null) {
			
			return ResponseEntity.ok().body(usuarioDTO);

		} else 
			return ResponseEntity.status(401).build();
			
	}
}