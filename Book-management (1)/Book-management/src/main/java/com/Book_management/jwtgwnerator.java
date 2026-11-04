package com.Book_management;

import java.security.SecureRandom;
import java.util.Base64;

public class jwtgwnerator {
	
	public static void main(String[] args) {
		byte[] key=new byte[32];
		
		SecureRandom secure=new SecureRandom();
		secure.nextBytes(key);
		
		String secretkey=Base64.getEncoder().encodeToString(key);
		
		System.out.println(secretkey);
	}

}
