package com.loteria.loteriaapi.domain.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.loteria.loteriaapi.domain.ValidationsGroup;

import lombok.Data;

@Data
@Entity
public class Bets {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationsGroup.PlayerId.class)
	@NotNull	
	@ManyToOne	
	private Player player;
	
	@Column(nullable = false)
	private String bet;	
	
	public Bets(Player player, String bet) {		
		this.player = player;
		this.bet = bet;
	}

	public Bets() {
	
	}
	
}
