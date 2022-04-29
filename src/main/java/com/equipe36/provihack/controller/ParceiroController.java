package com.equipe36.provihack.controller;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.equipe36.provihack.model.Parceiro;
import com.equipe36.provihack.repository.ParceiroRepository;

@RestController
@RequestMapping("/parceiros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParceiroController {

	@Autowired
	private ParceiroRepository parceiroRepository;

	@GetMapping
	public ResponseEntity<List<Parceiro>> GetAllParceiros() {
		return ResponseEntity.ok(parceiroRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Parceiro> GetParceiroById(@PathVariable Long id) {
		return parceiroRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

}
