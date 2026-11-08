package com.Book_management.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class jwtservice {
	
	@Value("${jwt.secret}")
	private String secretkey;
	@Value("${jwt.expiration}")
	private long expiration;
	
	public SecretKey generatingkey() {
		
		byte [] keybytes =Decoders.BASE64.decode(secretkey);
		
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public String createjwt(String mobilenumber) {
		
		Date date=new java.util.Date();
		
		return Jwts.builder().subject(mobilenumber).issuedAt(date).
				expiration(new Date(date.getTime()+expiration)).signWith(generatingkey()).compact();
		
	}
	
	public Claims extractClaims(String token) {
		
		return Jwts.parser().verifyWith(generatingkey()).build().parseSignedClaims(token).getPayload();
	}
	
	public String extractnumber(String token) {
		
		return extractClaims(token).getSubject();
		
	}
	
	public boolean istokenexpired(String token) {
		
		return extractClaims(token).getExpiration().before(new Date());
	}
	
	public boolean tokenvalidation(String mobilenumber,String token) {
		
		return extractnumber(token).equals(mobilenumber) && !istokenexpired(token);
	}

}
