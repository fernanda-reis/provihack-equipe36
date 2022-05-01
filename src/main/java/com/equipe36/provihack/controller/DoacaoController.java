package com.equipe36.provihack.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipe36.provihack.dto.DoacaoDTO;
import com.equipe36.provihack.model.Doacao;
import com.equipe36.provihack.model.Ong;
import com.equipe36.provihack.model.Usuario;
import com.equipe36.provihack.repository.DoacaoRepository;
import com.equipe36.provihack.repository.OngRepository;
import com.equipe36.provihack.repository.UsuarioRepository;

@RestController
@RequestMapping("/doacao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoacaoController {
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	private OngRepository ongRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("novo")
	public ResponseEntity<Doacao> NewDoacao(@RequestBody DoacaoDTO doacao){
		Ong ong = new Ong();
		ong = ongRepository.findById(doacao.getIdOng()).get();
		
		Usuario usuario = new Usuario();
		usuario = usuarioRepository.findById(doacao.getIdUsuario()).get();
		
		Doacao newDoacao = new Doacao();
		newDoacao.setData(new Date());
		newDoacao.setValorDoado(usuario.getTotalCashback());
		usuario.setTotalCashback(0);
		newDoacao.setOng(ong);
		newDoacao.setUsuario(usuario);
		
		return ResponseEntity.ok(doacaoRepository.saveAndFlush(newDoacao));		
	}

	@GetMapping
	public ResponseEntity<List<Doacao>> GetAllDoacoes() {
		return ResponseEntity.ok(doacaoRepository.findAll());
	}
}
