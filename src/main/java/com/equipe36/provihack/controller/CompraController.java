package com.equipe36.provihack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equipe36.provihack.dto.CompraDTO;
import com.equipe36.provihack.model.Compra;
import com.equipe36.provihack.model.Parceiro;
import com.equipe36.provihack.model.Usuario;
import com.equipe36.provihack.repository.CompraRepository;
import com.equipe36.provihack.repository.ParceiroRepository;
import com.equipe36.provihack.repository.UsuarioRepository;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompraController {

	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ParceiroRepository parceiroRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("novo")
	public ResponseEntity<Compra> NewCompra(@RequestBody CompraDTO compra){
		Parceiro parceiro = new Parceiro();
		parceiro = parceiroRepository.findById(compra.getIdParceiro()).get();
		
		Usuario usuario = new Usuario();
		usuario = usuarioRepository.findById(compra.getIdUsuario()).get();
		
		Compra newCompra = new Compra();
		newCompra.setCashback(compra.getCashback());
		newCompra.setData(compra.getData());
		newCompra.setValor(compra.getValor());
		newCompra.setParceiro(parceiro);
		newCompra.setUsuario(usuario);
		return ResponseEntity.ok(compraRepository.saveAndFlush(newCompra));
		
	}
	
	@GetMapping("cashback/total")
	public ResponseEntity<Float> cashbackTotal(){
		return ResponseEntity.ok(compraRepository.sumCashBacks());
	}
}