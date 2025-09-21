package com.example.SpringTeste.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringTeste.entities.Usuario;
import com.example.SpringTeste.repositories.UsuarioRepository;


@Service
public class UsuarioService {
	
	//objeto do tipo UsuarioRepository (o repositório que acessa o banco de dados).
	private final UsuarioRepository repository;
	
	//Injeção de dependência do repository
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
}
	
	//Método para salvar usuários no banco de dados
	public String SalvarUsuario(Usuario usuario) {
		repository.save(usuario);
		return "Usuário cadastrado com sucesso!!!";
	}
	
	//Métodos para procurar um usuário (pelo e-mail e pelo ID)
	public Usuario buscaId(Integer id) {
		return repository.findById(id).orElseThrow(
				()	-> new RuntimeException("Id não encontrado")
		);
	}
	
	public Usuario buscaEmail(String email) {
		return repository.findByEmail(email).orElseThrow(
				()	-> new RuntimeException("Email não encontrado")
		);
	}
	
	//Métodos para deletar um usuário (pelo e-mail e pelo Id)
	public String deletaPorEmail(String email) {
		repository.deleteByEmail(email);
		return "Uruário deletado";
	}
	
	public List<Usuario> procuraTodos(){
		return repository.findAll();
	}
	
	public void deletaPorId(Integer id) {
		repository.deleteById(id);
	}
	
	public String atualizarUsuario(Integer id, Usuario usuario) {
		Usuario response = repository.findById(id).get();
		
		response.setEmail(usuario.getEmail());
		response.setNome(usuario.getNome());
		response.setSenha(usuario.getSenha());
		
		repository.save(response);
		return "Uruário editado com sucesso!";
		
			
	}
}
