package com.loteria.loteriaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loteria.loteriaapi.domain.model.Bets;
import com.loteria.loteriaapi.domain.model.Player;
import com.loteria.loteriaapi.domain.service.BetsService;
import com.loteria.loteriaapi.dto.BetsDTO;

@RestController
@RequestMapping("/bets")
public class BetsController {
	
	@Autowired
	private BetsService betsService;
	
    @GetMapping("player/id/{playerId}")    
	public ResponseEntity<List<BetsDTO>> find(@PathVariable Long playerId) { 	

    	return ResponseEntity.ok(betsService.findByPlayerId(playerId));
    	
	}
    
    @GetMapping("player/email/{playerEmail}")    
	public ResponseEntity<List<BetsDTO>> find(@PathVariable String playerEmail) { 	

    	return ResponseEntity.ok(betsService.findByPlayerEmail(playerEmail));
    	
	}    
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Bets addBet(@RequestBody Player player) throws Exception{
		
		return betsService.createBetsUser(player);

	}
	
}
