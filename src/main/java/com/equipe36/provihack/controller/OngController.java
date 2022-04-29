package com.equipe36.provihack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipe36.provihack.model.Ong;
import com.equipe36.provihack.model.Parceiro;
import com.equipe36.provihack.repository.OngRepository;

@RestController
@RequestMapping("/ongs")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OngController {
	
	@Autowired
	private OngRepository ongRepository;
	
	@GetMapping
	public ResponseEntity<List<Ong>> GetAllOngs(){
		return ResponseEntity.ok(ongRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ong> GetParceiroById(@PathVariable Long id) {
		return ongRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

}
