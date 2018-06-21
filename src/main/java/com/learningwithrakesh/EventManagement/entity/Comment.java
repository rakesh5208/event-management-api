package com.learningwithrakesh.EventManagement.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@SuppressWarnings("javadoc")
@Entity
public class Comment extends BaseDomain {
	@Column(length = 20000)
	private byte[] message;

	@Column(length = 200)
	private String author;

	@ManyToOne(fetch = FetchType.EAGER)
	private Event event;

	@ManyToOne()
	private Comment reply;

	@OneToMany(mappedBy = "reply")
	private List<Comment> replies = new ArrayList<>();

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

	// public Comment getReply() {
	// return reply;
	// }

	public void setReply(Comment reply) {
		this.reply = reply;
	}

	// public List<Comment> getReplies() {
	// return replies;
	// }

	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "Comment [message=" + Arrays.toString(message) + ", author=" + author + ", event=" + event + ", reply="
				+ reply + ", replies=" + replies + "]";
	}

}
