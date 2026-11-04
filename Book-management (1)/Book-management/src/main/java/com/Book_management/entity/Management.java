package com.Book_management.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@SequenceGenerator(
		name = "issue_seq",
		sequenceName = "gen_seq",
		initialValue = 1001,
		allocationSize = 1
		)
@Table(name = "book_management")
public class Management {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "issue_seq")
	private int issueid;
	
	@ManyToOne()
	private Student stud;
	
	@ManyToOne()
	private Books book;
	
	private LocalDate issuedate;
	private LocalDate returndate;
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getIssueid() {
		return issueid;
	}
	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}
	public Student getStud() {
		return stud;
	}
	public void setStud(Student stud) {
		this.stud = stud;
	}
	public Books getBook() {
		return book;
	}
	public void setBook(Books book) {
		this.book = book;
	}
	public LocalDate getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(LocalDate issuedate) {
		this.issuedate = issuedate;
	}
	public LocalDate getReturndate() {
		return returndate;
	}
	public void setReturndate(LocalDate returndate) {
		this.returndate = returndate;
	}
	
	
}
