package com.Book_management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Book_management.entity.admintable;

public interface adminreposetory extends JpaRepository<admintable, Integer> {

	public admintable findByMobilenumber(long mobilenumber);
	
	public boolean existsByMobilenumber(long mobilenumber);
}
