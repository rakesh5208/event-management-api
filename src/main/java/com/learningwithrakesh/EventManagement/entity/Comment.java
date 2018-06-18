package com.learningwithrakesh.EventManagement.entity;

import javax.persistence.Entity;

@SuppressWarnings("javadoc")
@Entity
public class Comment extends BaseDomain {
	private String message;
	private String author;

	public Comment() {

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
