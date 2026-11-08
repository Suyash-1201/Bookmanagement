package com.Book_management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Book_management.entity.admintable;
import com.Book_management.reposetory.adminreposetory;
import com.Book_management.service.adminservice;
import com.Book_management.service.jwtservice;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class admincontroller {

	@Autowired
	private adminservice adserv;
	@Autowired
	private jwtservice jserv;
	@Autowired
	private adminreposetory adrepo;
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/save")
	public ResponseEntity<?> insertadmin(@RequestBody admintable data){
		admintable admindata=adserv.saveadmin(data);
		
		return ResponseEntity.ok().body(admindata);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginadmin(@RequestBody admintable data,HttpServletResponse http){
		
		Authentication auth=manager.authenticate
				( new UsernamePasswordAuthenticationToken(String.valueOf(data.getMobilenumber()),data.getPass(),null));
		
		String token=jserv.createjwt(auth.getName());
		
		ResponseCookie res= ResponseCookie.from("accesstoken",token)
				  .httpOnly(true)
			        .secure(false)
			        .path("/")
			        .maxAge(60 * 60)
			        .build();

			http.addHeader(HttpHeaders.SET_COOKIE, res.toString());
			
			return ResponseEntity.ok(Map.of("message","Login successfull","number",data.getMobilenumber(),"role",data.getRole()));
	}
}
