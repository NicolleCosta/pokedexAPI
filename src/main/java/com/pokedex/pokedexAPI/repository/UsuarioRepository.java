package com.pokedex.pokedexAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pokedex.pokedexAPI.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("from usuarios where usuario = :usuario and senha = :senha")
	public Usuario findByUsuarioSenha(
			@Param("usuario") String usuario,
			@Param("senha") String senha);
	

}
