package com.algaworks.api.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {

	public static void main(String[] args) {
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		String encode = e.encode("admin");
		System.out.println(encode);
	}
	
}
