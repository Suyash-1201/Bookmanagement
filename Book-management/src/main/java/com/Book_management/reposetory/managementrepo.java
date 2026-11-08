package com.Book_management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.Book_management.entity.Management;

public interface managementrepo extends JpaRepository<Management, Integer> {

	public boolean existsByStud_Sid(int sid);
	public void deleteByStud_Sid(int sid);
}
