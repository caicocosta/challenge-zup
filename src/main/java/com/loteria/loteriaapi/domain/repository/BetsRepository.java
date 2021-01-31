package com.loteria.loteriaapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loteria.loteriaapi.domain.model.Bets;

@Repository
public interface BetsRepository extends JpaRepository<Bets, Long>{
	Bets findByid(Long id);
	
	List<Bets> findByPlayerId(Long id);
	List<Bets> findByPlayerEmail(String email);
	Bets findByBet(String bet);
}
