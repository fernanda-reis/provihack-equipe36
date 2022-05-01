package com.equipe36.provihack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.equipe36.provihack.model.Parceiro;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
	
	List<Parceiro> findAllByCategoriaIgnoreCase(String categoria);
}

