package com.Book_management.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class globalexception {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleexception(Exception ex,HttpServletRequest http){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
		(Map.of("message",ex.getMessage(),"path",http.getRequestURI(),"status",HttpStatus.CONFLICT));
		
	}
}
