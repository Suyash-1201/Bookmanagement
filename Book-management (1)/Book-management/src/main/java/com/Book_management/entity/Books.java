package com.Book_management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(
		
		name = "seq",
		sequenceName = "gen_seq",
		initialValue = 101,
		allocationSize = 1
		)
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
	private int bid;
	private String bname;
	private int stock;
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
