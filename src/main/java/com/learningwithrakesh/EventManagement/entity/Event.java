package com.learningwithrakesh.EventManagement.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 */
@SuppressWarnings("javadoc")
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Event extends BaseDomain {
	@Column(length = 20000)
	private byte[] description;

	private String title;

	private Long startDateAndTime;

	private int duration;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Color> colors = new ArrayList<>();

	@OneToMany(mappedBy = "event")
	private List<Comment> comments = new ArrayList<>();

	/*
	 * public Event() { super(); }
	 */

	public String getTitle() {
		return title;
	}

	public byte[] getDescription() {
		return description;
	}

	public void setDescription(byte[] description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStartDateAndTime() {
		return startDateAndTime;
	}

	public void setStartDateAndTime(Long startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Color> getColors() {
		return colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	// public List<Comment> getComments() {
	// return comments;
	// }
	//
	// public void setComments(List<Comment> comments) {
	// this.comments = comments;
	// }

	@Override
	public String toString() {
		return "Event [description=" + description + ", title=" + title + ", startDateAndTime=" + startDateAndTime
				+ ", duration=" + duration + ", whenCreated= " + getWhenCreated() + ", whenLastUpdated= "
				+ getWhenLastUpdated() + ", colors=" + colors + "]";
	}

}
