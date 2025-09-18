package com.example.SpringTeste.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringTeste.Services.UsuarioService;
import com.example.SpringTeste.Entity.Usuario;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {

	//Auto Injeção de dependência do service
	@Autowired
	private UsuarioService service;	
	
	@GetMapping
	public List<Usuario> procuraTodos(){
		return service.procuraTodos();
	}
	
	@GetMapping
	public Usuario procuraPorId(@PathVariable Integer id) {
		return service.buscaId(id);
	}
	
	@GetMapping
	public Usuario procuraPorEmail(@PathVariable String email) {
		return service.buscaEmail(email);
	}
	@DeleteMapping
	public void excluiUsuario(@PathVariable Integer id) {
		service.deletaPorId(id);
	}
	
	@PostMapping
	public String adicionaUsuario(@RequestBody Usuario usuario) {
		String response = service.SalvarUsuario(usuario);
		return response;
	}
	@PutMapping
	public String editaUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		String response = service.atualizarUsuario(id, usuario);
		return response;
	}
	
}
