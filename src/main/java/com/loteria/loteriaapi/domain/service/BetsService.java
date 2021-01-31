package com.loteria.loteriaapi.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loteria.loteriaapi.domain.exception.BusinessExcepetion;
import com.loteria.loteriaapi.domain.model.Bets;
import com.loteria.loteriaapi.domain.model.Player;
import com.loteria.loteriaapi.domain.repository.BetsRepository;
import com.loteria.loteriaapi.domain.repository.PlayerRepository;
import com.loteria.loteriaapi.dto.BetsDTO;

@Service
public class BetsService {
	
	@Autowired
	private BetsRepository betsRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public List<BetsDTO> findByPlayerId(Long id) {	
		List<BetsDTO> allBetsDTO =  new ArrayList<>();		
		List<Bets> betsSaved = betsRepository.findByPlayerId(id);	
		
		if (betsSaved.isEmpty()) {
			throw new BusinessExcepetion("Nenhuma aposta foi realizada por esse usuário!");
		}
		
		for(Bets bets : betsSaved) {
			
			BetsDTO betsDTO = new BetsDTO();			
			betsDTO.setName(bets.getPlayer().getName());
			betsDTO.setEmail(bets.getPlayer().getEmail());
			betsDTO.setBet(bets.getBet());
			
			allBetsDTO.add(betsDTO);	
		}
	
		return allBetsDTO; 
	}	
	
	public List<BetsDTO> findByPlayerEmail(String email) {	
		List<BetsDTO> allBetsDTO =  new ArrayList<>();		
		List<Bets> betsSaved = betsRepository.findByPlayerEmail(email);	
		
		if (betsSaved.isEmpty()) {
			throw new BusinessExcepetion("Nenhuma aposta foi realizada por esse usuário!");
		}
		
		for(Bets bets : betsSaved) {
			
			BetsDTO betsDTO = new BetsDTO();			
			betsDTO.setName(bets.getPlayer().getName());
			betsDTO.setEmail(bets.getPlayer().getEmail());
			betsDTO.setBet(bets.getBet());
			
			allBetsDTO.add(betsDTO);	
		}
	
		return allBetsDTO; 
	}		
	
	public Bets createBets(Player player) {
	
	    Bets bets = new Bets(player, numbersGenerator(player));		
		Bets betsSaved = betsRepository.save(bets);
		
		return betsSaved;
		
	}
	
	
	public Bets createBetsUser(Player player) {
		
		Player newPlayer = playerRepository.findByEmail(player.getEmail());
				
		if (newPlayer == null) {
			new BusinessExcepetion("Usuário não cadastrado!");
		}					
		
		return createBets(newPlayer);		
		
	}
	
	private String numbersGenerator(Player player) {		

		String numbers = "";		
		List<Integer> previousNumber = new ArrayList<>();		
		
		for(int i = 0; i < 6; i++) {
			
	        Random r = new Random();
	        Integer aux = r.nextInt(60) + 1;
			
	        if (previousNumber.contains(aux)) {
	        	i--;
	        } else {
	        	previousNumber.add(aux);
	        }
		}
		
		previousNumber.sort(null);
		
		for (int i = 0; i < previousNumber.size() ; i++) {			
			
			if (i == 0) {
				if (previousNumber.get(i) < 10) {
					numbers = "0" + previousNumber.get(i);
				} else {
					numbers = previousNumber.get(i).toString();
				}
			} else {
				if (previousNumber.get(i) < 10) {
					numbers = numbers + ":0" + previousNumber.get(i); 
				} else {
					numbers = numbers + ":" + previousNumber.get(i);
				}
			}
		}	
		
		
		Bets betsExists = betsRepository.findByBet(numbers);
		
		if (betsExists != null) {
			if (betsExists.getPlayer().getId() == player.getId()) {
				numbers = numbersGenerator(player);	
			}			
		}
		
		return numbers;
	}	
}
