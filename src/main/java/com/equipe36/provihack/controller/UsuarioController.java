package com.equipe36.provihack.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.equipe36.provihack.model.Usuario;
import com.equipe36.provihack.model.UsuarioLogin;
import com.equipe36.provihack.repository.UsuarioRepository;
import com.equipe36.provihack.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private UsuarioService service;

	
	@GetMapping("/{cpf}")
	public ResponseEntity<Usuario> GetByCpf(@PathVariable String cpf){
		return repository.findByCpf(cpf)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Usuario> GetByCpf(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());	
	}
	
	
	/**
	 * exemplo json:
	 * {
	    "cpf":"1111111",
	    "senha":"12345"
		}
	 */
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@Valid @RequestBody Optional<UsuarioLogin> user){
		return service.Logar(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());	
	}
	
	
	/**
	 * exemplo json:
	 * {
	    "cpf":"1111111",
	    "nome":"Fernanda",
	    "senha":"12345"
		}
	 * */
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@Valid @RequestBody Usuario user){
		return service.CadastrarUsuario(user)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElseGet(() -> { 
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!");
				});
	}
	
	@PutMapping
	public ResponseEntity<Usuario> Put(@RequestBody Usuario user) {
		return service.AtualizarUsuario(user)
				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
				.orElseGet(() -> { 
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos!");
				});
	}
	
	
	
//	@PatchMapping("/{cpf}")
//	public ResponseEntity<Usuario> Patch(@PathVariable String cpf, @RequestBody String senhaNova) {
//		return service.AtualizarSenha(cpf, senhaNova)
//				.map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
//				.orElseGet(() -> { 
//					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados incorretos!");
//				});
//	}
	
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity Delete(@PathVariable long id) {
		Optional<Usuario> optional = repository.findById(id);
		if(optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	
}
