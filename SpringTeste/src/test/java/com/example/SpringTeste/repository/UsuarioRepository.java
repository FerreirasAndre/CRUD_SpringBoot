package com.example.SpringTeste.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringTeste.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{
	//Criação de método para busca por E-mail
	Optional<Usuario> findByEmail(String email);
	void deletByEmail(String email);

}
