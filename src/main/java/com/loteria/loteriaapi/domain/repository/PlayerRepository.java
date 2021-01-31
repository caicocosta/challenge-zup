package com.loteria.loteriaapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loteria.loteriaapi.domain.model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{

	Player findByEmail(String email);
}
