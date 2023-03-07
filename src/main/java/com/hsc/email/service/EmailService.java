package com.hsc.email.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



@Service
public class EmailService {

	public boolean sendEmail(String to, String subject, String message) {

		boolean result = false;

		String from = "sharmagkedubs@gmail.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "dndkacixbxzdqjtc");
			}

		});

		session.setDebug(true);
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(from);
			mimeMessage.setSubject(subject);
//			mimeMessage.setText(message);
			mimeMessage.setContent(message,"text/html");
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			Transport.send(mimeMessage);
			System.out.println("Mail sent successfully");
			result=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
