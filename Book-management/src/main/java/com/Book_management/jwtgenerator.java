package com.Book_management;

import java.security.SecureRandom;
import java.util.Base64;

public class jwtgenerator {

	public static void main(String[] args) {
		byte [] key=new byte[32];
		
		SecureRandom random=new SecureRandom();
		
		random.nextBytes(key);
		
		String secretkey=Base64.getEncoder().encodeToString(key);
		
		System.out.println(secretkey);
	}
}
