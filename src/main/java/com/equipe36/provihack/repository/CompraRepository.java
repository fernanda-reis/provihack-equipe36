package com.equipe36.provihack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.equipe36.provihack.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	@Query("SELECT SUM(c.cashback) FROM Compra c")
	Float sumCashBacks();

}
