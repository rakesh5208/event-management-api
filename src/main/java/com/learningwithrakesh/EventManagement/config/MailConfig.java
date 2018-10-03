package com.learningwithrakesh.EventManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learningwithrakesh.EventManagement.dto.MailProperties;

@Configuration
public class MailConfig {

	@Bean
	public MailProperties mailProperties() {
		MailProperties mailProp = new MailProperties()
				.withProperty("mail.smtp.host","smtp.gmail.com")
				.withProperty("mail.smtp.port", "587")
				.withProperty("mail.smtp.auth", "true")
				.withProperty("mail.smtp.starttls.enable", "true");
		return mailProp;
	}
}
