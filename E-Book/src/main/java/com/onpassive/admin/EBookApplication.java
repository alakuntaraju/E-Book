package com.onpassive.admin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EBookApplication {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(EBookApplication.class);

	public static void main(String[] args) {
		LOGGER.debug("main method start");
		SpringApplication.run(EBookApplication.class, args);
		LOGGER.debug("main method end");
	}

}
