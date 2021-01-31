package com.loteria.loteriaapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.loteria.loteriaapi.domain.ValidationsGroup;

import lombok.Data;

@Data
@Entity
public class Player {
	@NotNull(groups = ValidationsGroup.PlayerId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
}
