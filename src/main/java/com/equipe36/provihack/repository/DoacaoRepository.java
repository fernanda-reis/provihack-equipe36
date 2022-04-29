package com.equipe36.provihack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipe36.provihack.model.Doacao;

@Repository
public interface DoacaoRepository  extends JpaRepository<Doacao, Long>{

}
