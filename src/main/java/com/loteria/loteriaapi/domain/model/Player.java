package com.loteria.loteriaapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.loteria.loteriaapi.domain.ValidationsGroup;

import lombok.Data;

@Data
@Entity
public class Player {
	@NotNull(groups = ValidationsGroup.PlayerId.class)
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	@Email
	private String email;
}
