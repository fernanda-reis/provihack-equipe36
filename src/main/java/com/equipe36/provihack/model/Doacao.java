package com.equipe36.provihack.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doacao")
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private float valorDoado;
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "ong_id")
	@JsonIgnoreProperties("doacoes")
	private Ong ong;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	@JsonIgnoreProperties("doacoes")
	private Usuario usuario;
	
}
