package com.Book_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.Book_management.entity.Books;
import  com.Book_management.entity.Issuedto;
import  com.Book_management.entity.Management;
import com.Book_management.entity.Student;
import com.Book_management.reposetory.bookrepo;
import com.Book_management.reposetory.managementrepo;
import  com.Book_management.reposetory.studentrepo;

@Service
public class bookservice {
	
	@Autowired
	private bookrepo repo;
	
	@Autowired
	private studentrepo srepo;
	
	@Autowired
	private managementrepo mrepo;

	
//	add books
	public Books addbook(Books b) {
		Books exist=repo.findByBname(b.getBname());
		if(exist != null) {

			if(exist.getBname().equals(b.getBname())) {
				exist.setStock(exist.getStock()+b.getStock());
				return repo.save(exist);
			}
		}
		return repo.save(b);
		
	}
//	get all books
	public List<Books> allbooks(){
		return repo.findAll();
	}
	
//	delete book
	public void deletebook(int id) {
		Books exist=repo.findById(id).orElse(null);
		
		if(exist == null) {
			throw new RuntimeException("Book not found");
		}
		repo.deleteById(id);
	}
	
//	add students
	public Student addStudent(Student s) {
		
		return srepo.save(s);
	}
	
//	get all students
	public List<Student> allstud(){
		return srepo.findAll();
	}
	
//	delete student 
	@Transactional
	public void deletestud(int id) {
		System.out.println(id);
		if(mrepo.existsByStud_Sid(id) == false) {
			throw new RuntimeException("The data is not found");
		}
		mrepo.deleteByStud_Sid(id);
		srepo.deleteById(id);
	}
	
//	add issue
	
	public Management addissue(Issuedto m) {
		
		Student studname=srepo.findBySname(m.getSname());
		
		Student stud=srepo.findByNumber(m.getNumber());
		
		Books book=repo.findByBname(m.getBname());
		
		System.out.println(m.getBname());
		
		if(studname == null) {
			throw new RuntimeException("Student name is not registed");
		}

		if(stud == null) {
			throw new RuntimeException("Student is not available");
		}
		

		if(book == null) {
			throw new RuntimeException("Book is not available");
		}
		
		if(book.getStock() <= 0) {
			throw new RuntimeException("stock unavailable");
		}
		else {
			book.setStock(book.getStock()-1);
		}
		
		Management manage=new Management();
		
		manage.setStud(stud);
		manage.setBook(book);
		manage.setIssuedate(LocalDate.now());
		manage.setStatus(false);
		
		return mrepo.save(manage);
	}
	
//	get all issue
	public List<Management> allissue(){
		return mrepo.findAll();
	}
	
//	update the return date and status
	public Management updatestatus(int id) {
		Management exist=mrepo.findById(id).orElse(null);
		
		if(exist == null) {
			throw new RuntimeException("Not found");
			
		}
		
		exist.setReturndate(LocalDate.now());
		exist.setStatus(true);
		
		return mrepo.save(exist);
	}
}
