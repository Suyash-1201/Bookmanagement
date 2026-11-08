package com.Book_management.config;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.Book_management.service.jwtauthservice;

@Configuration
@Component
public class securityconfig  {
	
	@Autowired
	private jwtauthservice jwtauth;
	
	@Bean
	public PasswordEncoder passencode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager manager(AuthenticationConfiguration con) throws Exception {
		
		return con.getAuthenticationManager();
		
	}
	
	@Bean
	public SecurityFilterChain filterchian(HttpSecurity http) {
		
		http.cors(cor->cor.configurationSource(corscofig())).csrf(c->c.disable())
		.authorizeHttpRequests(auth->auth.anyRequest().permitAll()).addFilterBefore(jwtauth, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
		
	}
	
	@Bean
	public CorsConfigurationSource corscofig() {
		CorsConfiguration configuration=new CorsConfiguration();
		configuration.setAllowedOrigins( List.of( "http://localhost:5173","http://localhost:3000")); 
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type","X-XSRF-TOKEN", "X-Requested-With"));
	    configuration.setAllowCredentials(true); 
	    
	    UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**" ,configuration);
		return  source;
		
	}

}
