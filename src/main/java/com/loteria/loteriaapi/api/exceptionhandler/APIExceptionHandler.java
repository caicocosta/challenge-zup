package com.loteria.loteriaapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.loteria.loteriaapi.domain.exception.BusinessExcepetion;


@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(BusinessExcepetion.class)
	public ResponseEntity<Object> handleNegocio(BusinessExcepetion ex, WebRequest request) {
		
		var status = HttpStatus.BAD_REQUEST;
		
		var trouble = new Trouble();
		
		trouble.setStatus(status.value());
		trouble.setDataHora(OffsetDateTime.now());
		trouble.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, trouble, new HttpHeaders(), status, request);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var campos = new ArrayList<Trouble.Campo>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			
			campos.add(new Trouble.Campo(nome, mensagem));
		}
		
		
		
		var trouble = new Trouble();
		
		trouble.setStatus(status.value());		
		trouble.setTitulo("Um ou mais campos estão inválidos. " 
				+ "Faça o preenchimento correto e tente novamente");
		trouble.setDataHora(OffsetDateTime.now());
		trouble.setCampos(campos);
		
		return super.handleExceptionInternal(ex, trouble, headers, status, request);
	}
}
