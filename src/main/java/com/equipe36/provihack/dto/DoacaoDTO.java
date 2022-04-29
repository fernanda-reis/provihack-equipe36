package com.equipe36.provihack.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoDTO {

	private float valor;
	
	private Date data;
	
	private Long idUsuario;
	
	private Long idOng;
	
}
