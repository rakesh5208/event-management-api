package com.learningwithrakesh.EventManagement.dto;

import java.util.Arrays;

public class EmailTemplate {
	private String []recipients;
	private String subject;
	private String body;
	
	public String[] getRecipients() {
		return recipients;
	}
	public void setRecipients(String []sendTo) {
		this.recipients = sendTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "EmailTemplate [sendTo=" + Arrays.toString(recipients) + ", subject=" + subject + ", body=" + body + "]";
	}
	
	
}
