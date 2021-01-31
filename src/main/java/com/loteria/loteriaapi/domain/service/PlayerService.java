package com.loteria.loteriaapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.loteria.loteriaapi.domain.exception.BusinessExcepetion;
import com.loteria.loteriaapi.domain.model.Bets;
import com.loteria.loteriaapi.domain.model.Player;
import com.loteria.loteriaapi.domain.repository.PlayerRepository;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;	
	
	@Autowired
	private BetsService betsService;
	
	public ResponseEntity<Bets> save (Player player) {
		
		Player playerExistente = playerRepository.findByEmail(player.getEmail());
		
		if (playerExistente != null) {
			throw new BusinessExcepetion("Já existe um apostador cadastrado com esse e-mail.");
		}		
		
		player = playerRepository.save(player);		
	
		Bets bets = betsService.createBets(player);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bets);
	}
}
