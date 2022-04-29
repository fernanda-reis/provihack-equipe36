package com.equipe36.provihack.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.equipe36.provihack.model.Compra;
import com.equipe36.provihack.model.Parceiro;
import com.equipe36.provihack.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
	
	private float valor;
	
	private int cashback;
	
	private Date data;
	
	private Long idUsuario;
	
	private Long idParceiro;
	
}
