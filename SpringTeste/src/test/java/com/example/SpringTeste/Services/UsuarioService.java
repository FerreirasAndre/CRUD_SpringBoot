package com.example.SpringTeste.Services;
import org.springframework.stereotype.Service;
import com.example.SpringTeste.Entities.Usuario;
import com.example.SpringTeste.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	//objeto do tipo UsuarioRepository (o repositório que acessa o banco de dados).
	private final UsuarioRepository repository;
	
	//Injeção de dependência do repository
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
}
	
	//Método para salvar usuários no banco de dados
	public void SalvarUsuario(Usuario usuario) {
		repository.saveAndFlush(usuario);
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
	public void deletaPorEmail(String email) {
		repository.deletByEmail(email);
	}
	
	public void deletaPorId(Integer id) {
		repository.deleteById(id);
	}
	
	public void atualizarUsuario(Integer id, Usuario usuario) {
		Usuario usuarioEntity = buscaId(id);
		Usuario usuarioAtualizado = Usuario.builder()
				.id(usuario.getId() != null?
						usuario.getId(): usuarioEntity.getId())
				.build()
	}
}
