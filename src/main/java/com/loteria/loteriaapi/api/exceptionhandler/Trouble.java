package com.loteria.loteriaapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Trouble {
	private Integer status;
	private OffsetDateTime DataHora;
	private String Titulo;	
	private List<Campo> campos;
	
	@Data
	public static class Campo {				
		
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		
		private String nome;
		private String mensagem;				
	}

}
