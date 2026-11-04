package com.Book_management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Book_management.entity.Student;

public interface studentrepo extends JpaRepository<Student, Integer> {

	public Student findByNumber(long number);
	
	public Student findBySname(String name);
}
