package com.loteria.loteriaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loteria.loteriaapi.domain.model.Bets;
import com.loteria.loteriaapi.domain.model.Player;
import com.loteria.loteriaapi.domain.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

		
	@Autowired
	private PlayerService playerService;	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Bets> createPlayer(@RequestBody Player player) {
		
		return playerService.save(player);
		
	}
}
