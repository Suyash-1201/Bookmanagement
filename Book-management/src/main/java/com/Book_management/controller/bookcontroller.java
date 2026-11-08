package com.Book_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.Book_management.entity.Books;
import  com.Book_management.entity.Issuedto;
import  com.Book_management.entity.Management;
import  com.Book_management.entity.Student;
import com.Book_management.reposetory.bookrepo;
import  com.Book_management.service.bookservice;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/manage")
public class bookcontroller {

	@Autowired
	private bookservice bookserv;
	
	
	@PostMapping("/savebook")
	public Books insertBooks(@RequestBody Books b) {
		return bookserv.addbook(b);
	}
	
//	get all books
	@GetMapping("/getbooks")
	public List<Books> getall(){
		return bookserv.allbooks();
	}
	
//	delete book
	@DeleteMapping("/deletebook/{id}")
	public void deletebookbyid(@PathVariable int id) {
		bookserv.deletebook(id);
	}
	
//	save student
	
	@PostMapping("/savestud")
	public Student insertStudent(@RequestBody Student s) {
		return bookserv.addStudent(s);
	}
	
//	get all students
	@GetMapping("/getstudents")
	public List<Student> getallstud(){
		return bookserv.allstud();
	}
	
//	delete student
	@DeleteMapping("/delete/student/{id}")
	public void delstud(@PathVariable int id) {
		bookserv.deletestud(id);
	}
	
//	issue book
	@PostMapping("/saveissue")
	public Management addmanage(@RequestBody Issuedto i) {
		
		return bookserv.addissue(i);
	}
	
//	get issue
	@GetMapping("/getissue")
	public List<Management> getallissue(){
		return bookserv.allissue();
	}
	
//	update return date and status
	@PutMapping("/updatestatus/{id}")
	public Management updateissue(@PathVariable int id) {
		return bookserv.updatestatus(id);
	}
}
