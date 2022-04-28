package com.equipe36.provihack.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLogin {

	private Long id;
	
	private String cpf;
	
	private String nome;
	
	private String foto;
		
	private String senha;
	
	private float totalCashback;
	
	private String token;
	
}
