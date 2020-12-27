package com.onpassive.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.onpassive.admin.mail.Email;

@SpringBootApplication
public class EBookApplication {
	
//	private static final Logger LOGGER=LoggerFactory.getLogger(EBookApplication.class);
	
//	@Autowired
//	   private static EmailService emailService;

	public static void main(String[] args) {
//		LOGGER.debug("main method start");
		SpringApplication.run(EBookApplication.class, args);
//		LOGGER.debug("main method end");
	}
	
//	Email email = new Email();
//    email.setFrom("from-mail1@gmail.com");
//    email.setTo("to-mail2@mgmail.com");
//    email.setSubject("This is a test mail");
//    email.setMessageText("This is a sample text message.");
//    emailService.sendMailWithAttachment(email,
//       "./samplepic.jpg");
 }
	
