package com.equipe36.provihack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
	
	private float valor;
	
	private float cashback;
	
	private Date data;
	
	private Long idUsuario;
	
	private Long idParceiro;
	
}
