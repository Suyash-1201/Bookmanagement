package com.Book_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Book_management.entity.admintable;
import com.Book_management.reposetory.adminreposetory;

@Service
public class usedetailservice implements UserDetailsService {
	
	@Autowired
	private adminreposetory adrepo;

	@Override
	public UserDetails loadUserByUsername(String mobilenumber) throws UsernameNotFoundException {
	    
		admintable data=adrepo.findByMobilenumber(Long.parseLong(mobilenumber));
		if(data == null) {
			throw new UsernameNotFoundException("Admin number not found ");
		}
		
		return User.withUsername(String.valueOf(data.getMobilenumber())).password(data.getPass()).roles(data.getRole()).build();
	}

}
