package com.learningwithrakesh.EventManagement.entity;

import java.util.ArrayList;
import java.util.List;

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
	private String description;
	private String title;
	private Long startDateAndTime;
	private int duration;
	@OneToMany(fetch = FetchType.EAGER)
	private List<Color> colors = new ArrayList<>();
	/*
	 * public Event() { super(); }
	 */

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
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

	@Override
	public String toString() {
		return "Event [description=" + description + ", title=" + title + ", startDateAndTime=" + startDateAndTime
				+ ", duration=" + duration + ", whenCreated= " + getWhenCreated() + ", whenLastUpdated= "
				+ getWhenLastUpdated() + ", colors=" + colors + "]";
	}

}
