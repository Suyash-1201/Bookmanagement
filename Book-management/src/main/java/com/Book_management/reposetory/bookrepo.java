package com.Book_management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;


import  com.Book_management.entity.Books;



public interface bookrepo extends JpaRepository<Books, Integer> {

	public Books findByBname(String bname);
}
