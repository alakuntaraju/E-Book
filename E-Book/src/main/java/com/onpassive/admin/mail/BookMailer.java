//package com.onpassive.admin.mail;
//
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//
////import java.util.Properties;
////import javax.mail.Authenticator;
////import javax.mail.PasswordAuthentication;
//
//public class BookMailer {
//	
//	public static void sendMailUsingTLS(String host,
//	         String username, String password, String from,
//	         String to, String subject, String text) {
//	      Properties properties = new Properties();
//	      properties.put("mail.smtp.host", host);
//	      properties.put("mail.smtp.auth", "true");
//	      properties.put("mail.smtp.starttls.enable", "true");
//	      properties.put("mail.smtp.port", "587");
//	      sendMail(properties, username, password, from, to,
//	         subject, text);
//	   }
//	   public static void sendMailUsingSSL(String host,
//	         String username, String password, String from,
//	         String to, String subject, String text) {
//	      Properties properties = new Properties();
//	      properties.put("mail.smtp.host", "smtp.gmail.com");
//	      properties.put("mail.smtp.socketFactory.port", "465");
//	      properties.put("mail.smtp.socketFactory.class",
//	         "javax.net.ssl.SSLSocketFactory");
//	      properties.put("mail.smtp.auth", "true");
//	      properties.put("mail.smtp.port", "465");
//	      sendMail(properties, username, password, from, to,
//	         subject, text);
//	   }
//	   public static void sendMail(Properties properties,
//	         String username, String password,
//	         String fromEmailAddress, String toEmailAddress,,
//	         String subject String messageText) {
//	      Session session = Session.getInstance(properties,
//	            new Authenticator() {
//	         @Override
//	         protected PasswordAuthentication
//	               getPasswordAuthentication() {
//	            return new PasswordAuthentication(username,
//	               password);
//	         }
//	      });
//	      try {
//	         Message msg = new MimeMessage(session);
//	         msg.setFrom(new InternetAddress(fromEmailAddress));
//	         msg.setRecipients(Message.RecipientType.TO,
//	         InternetAddress.parse(toEmailAddress));
//	         msg.setSubject(subject);
//	         msg.setText(messageText);
//	         Transport.send(msg);
//	      } catch (Exception ex) {
//	         ex.printStackTrace();
//	      }
//	   }
//	   public static void main(String[] args) {
//	      String host = "smtp.gmail.com";
//	      String username = "from-myemail1@gmail.com";
//	      String password = "******";
//	      String fromAddress = "from-myemail1@gmail.com";
//	      String toAddress = "to-myemail2@gmail.com";
//	      String subject = "Test mail";
//	      String text = "This is a sample message. Thank you.";
//	      BookMailer.sendMailUsingTLS(host, username, password,
//	      fromAddress, toAddress, subject, text);
//	      BookMailer.sendMailUsingSSL(host, username, password,
//	         fromAddress, toAddress, subject, text);
//	   }
//
//}
