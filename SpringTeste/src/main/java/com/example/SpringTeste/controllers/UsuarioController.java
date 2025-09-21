package com.example.SpringTeste.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringTeste.entities.Usuario;

import com.example.SpringTeste.services.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {


	private final UsuarioService service;	
	
	//Injeção de dependência do repository
	public UsuarioController(UsuarioService service) {
		this.service = service;
}
	
	@GetMapping
	public List<Usuario> procuraTodos(){
		return service.procuraTodos();
	}
	
	@GetMapping("/{id}")
	public Usuario procuraPorId(@PathVariable Integer id) {
		return service.buscaId(id);
	}
	
	@GetMapping("/email/{email}")
	public Usuario procuraPorEmail(@PathVariable String email) {
		return service.buscaEmail(email);
	}
	@DeleteMapping("/{id}")
	public void excluiUsuarioId(@PathVariable Integer id, @RequestBody Usuario usuario) {
		service.deletaPorId(id);
	}
	
	@DeleteMapping("/{email}")
	public void excluiUsuarioEmail(@PathVariable String email, @RequestBody Usuario usuario) {
		service.deletaPorEmail(email);
	}
	
	@PostMapping
	public String adicionaUsuario(@RequestBody Usuario usuario) {
		String response = service.SalvarUsuario(usuario);
		return response;
	}
	@PutMapping("/{id}")
	public String editaUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		String response = service.atualizarUsuario(id, usuario);
		return response;
	}
	
	
}
