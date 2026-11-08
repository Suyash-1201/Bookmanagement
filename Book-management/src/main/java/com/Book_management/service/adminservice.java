package com.Book_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Book_management.entity.admintable;
import com.Book_management.reposetory.adminreposetory;

@Service
public class adminservice {

	@Autowired
	private adminreposetory adrepo;
	@Autowired
	private PasswordEncoder passencode;
	@Autowired
	private usedetailservice useserv;
	
	
	public admintable saveadmin(admintable ad) {
		
		if(adrepo.existsByMobilenumber(ad.getMobilenumber())) {
			throw new RuntimeException("Admin already exist");
		}
		
		admintable data=new admintable();
		
		data.setAdminname(ad.getAdminname());
		data.setMobilenumber(ad.getMobilenumber());
		data.setRole("admin");
		data.setPass(passencode.encode(ad.getPass()));
		
		adrepo.save(data);
		
		data.setPass(null);
		return data;
		
	}
	
}
