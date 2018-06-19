package com.learningwithrakesh.EventManagement.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@SuppressWarnings("javadoc")
@Entity
public class Comment extends BaseDomain {
	@Column(length = 20000)
	private byte[] message;

	@Column(length = 200)
	private String author;

	@ManyToOne(fetch = FetchType.EAGER)
	private Event event;

	public Comment() {

	}

	public byte[] getMessage() {
		return message;
	}

	public void setMessage(byte[] message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Comment [message=" + Arrays.toString(message) + ", author=" + author + "]";
	}

}
