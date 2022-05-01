package com.equipe36.provihack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.equipe36.provihack.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	@Query("SELECT SUM(c.valorCashback) FROM Compra c")
	Float sumCashBacks();
	
	List<Compra> findAllByOrderByDataDesc();

}
