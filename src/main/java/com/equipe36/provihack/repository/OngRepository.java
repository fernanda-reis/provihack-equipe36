package com.equipe36.provihack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.equipe36.provihack.model.Ong;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long>{

}
