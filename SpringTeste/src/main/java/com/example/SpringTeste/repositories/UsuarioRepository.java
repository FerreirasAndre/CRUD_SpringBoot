package com.example.SpringTeste.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringTeste.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{
	//Criação de método para busca por E-mail
	Optional<Usuario> findByEmail(String email);
	void deleteByEmail(String email);

}
