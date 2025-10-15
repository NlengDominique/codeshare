package com.lndev.codeshut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CodeshutApplication {
	public static void main(String[] args) {
		SpringApplication.run(CodeshutApplication.class, args);
	}

}
