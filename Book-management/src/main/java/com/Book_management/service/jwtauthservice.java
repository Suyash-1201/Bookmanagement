package com.Book_management.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class jwtauthservice extends OncePerRequestFilter {

	@Autowired
	private usedetailservice userv;
	@Autowired
	private jwtservice jserv;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Cookie [] cookies=request.getCookies();
		String token=null;
		
		if(cookies == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		for (Cookie c : cookies) {
			if(c.getName().equals("accesstoken")) {
				token=c.getValue();
			}
		}
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		
		try {
			if(token != null && authentication == null && !token.trim().isEmpty()) {
				
				String mobilenumber=jserv.extractnumber(token);
				
				UserDetails users=userv.loadUserByUsername(mobilenumber);
				
				if(jserv.tokenvalidation(mobilenumber, token)) {
					
					UsernamePasswordAuthenticationToken usetoken=
							new UsernamePasswordAuthenticationToken(users.getUsername(),null,users.getAuthorities());
					
					WebAuthenticationDetailsSource source = new WebAuthenticationDetailsSource();
					usetoken.setDetails(source.buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(usetoken);
					
				}
				
			}
		} catch (Exception e) {
			logger.error(e);
		}
	
		filterChain.doFilter(request, response);
		
	}
	
}
