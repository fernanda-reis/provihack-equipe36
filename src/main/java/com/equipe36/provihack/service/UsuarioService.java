package com.equipe36.provihack.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.binary.Base64;
import java.nio.charset.Charset;

import com.equipe36.provihack.model.Usuario;
import com.equipe36.provihack.model.UsuarioLogin;
import com.equipe36.provihack.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> CadastrarUsuario(Usuario usuario){
		Optional<Usuario> retorno;
		Optional<Usuario> UsuarioVerifica = repository.findByCpf(usuario.getCpf());
		
		if (UsuarioVerifica.isEmpty()) {
			String senhaEncoder = CriptografarSenha(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			usuario.setTotalCashback(0);
			usuario.setFoto("https://i.imgur.com/8IhTbO0.jpg");
			repository.save(usuario);
			retorno = Optional.of(usuario);
		
		} else {retorno = Optional.empty();}

		return retorno;		
	}
	
	public String CriptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}
	
	public Optional<Usuario> AtualizarUsuario(Usuario usuario){
		Optional<Usuario> UsuarioVerifica = repository.findById(usuario.getId());
		if(UsuarioVerifica.isPresent()) {
			if(compararSenhas(usuario.getSenha(), UsuarioVerifica.get().getSenha())) {
				UsuarioVerifica.get().setCpf(usuario.getCpf());
				UsuarioVerifica.get().setFoto(usuario.getFoto());
				UsuarioVerifica.get().setNome(usuario.getNome());
				UsuarioVerifica.get().setTotalCashback(usuario.getTotalCashback());

				
				repository.save(UsuarioVerifica.get());
				return UsuarioVerifica;
			}
		}
		
		return Optional.empty();
	}
	
//	public Optional<Usuario> AtualizarSenha(String cpf, String senhaNova){
//		Optional<Usuario> usuario = repository.findByCpf(cpf);
//		if(usuario.isPresent()) {
//			
//				String senhaEncoder = CriptografarSenha(senhaNova);
//				usuario.get().setSenha(senhaEncoder);
//				
//				repository.save(usuario.get());
//				return usuario;
//
//		}
//		
//		return Optional.empty();
//	}
	
	
	
	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> userLogin) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<Usuario> usuario = repository.findByCpf(userLogin.get().getCpf());
		
		if (usuario.isPresent()) {
			if(encoder.matches(userLogin.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = userLogin.get().getCpf() + ":" + userLogin.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				
				String authHeader = "Basic " + new String(encodedAuth);
				
				userLogin.get().setToken(authHeader);
				
				userLogin.get().setId(usuario.get().getId());				
				userLogin.get().setNome(usuario.get().getNome());
				userLogin.get().setTotalCashback(usuario.get().getTotalCashback());
				userLogin.get().setFoto(usuario.get().getFoto());
				userLogin.get().setSenha("");
				
				return userLogin;
			}
		}
		
		return null;
	}
	

}
