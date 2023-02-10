package com.pokedex.pokedexAPI.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.pokedex.pokedexAPI.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query("from Usuario where nome = :login and senha = :senha")
	public Usuario findByLoginAndSenha(
			@Param("login") String usuario,
			@Param("senha") String senha);
}
