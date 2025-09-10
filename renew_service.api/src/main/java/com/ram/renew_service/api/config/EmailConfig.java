package com.ram.renew_service.api.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

//@Configuration
public class EmailConfig {

    //@Bean
	/*
	 * public JavaMailSender javaMailSender() { JavaMailSenderImpl mailSender = new
	 * JavaMailSenderImpl(); mailSender.setHost("smtp.gmail.com");
	 * mailSender.setPort(587); mailSender.setUsername("developers.mcg@gmail.com");
	 * mailSender.setPassword("elxiivgepvrgvdfo");
	 * 
	 * Properties props = mailSender.getJavaMailProperties();
	 * props.put("mail.transport.protocol", "smtp"); props.put("mail.smtp.auth",
	 * "true"); props.put("mail.smtp.starttls.enable", "true");
	 * props.put("mail.debug", "true"); props.put("mail.smtp.auth", "true");
	 * props.put("mail.smtp.connectiontimeout", "5000");
	 * props.put("mail.smtp.timeout", "5000"); props.put("mail.smtp.writetimeout",
	 * "5000"); props.put("mail.smtp.starttls.enable", "true");
	 * 
	 * Session session = Session.getDefaultInstance(props, new
	 * javax.mail.Authenticator() { protected PasswordAuthentication
	 * getPasswordAuthentication() { return new
	 * PasswordAuthentication(mailSender.getUsername(), mailSender.getPassword()); }
	 * }); mailSender.setSession(session); return mailSender; }
	 */}