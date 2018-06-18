/*******************************************************************************
 *  ============================================================================
 *                       Copyright (C) Pfizer Inc.
 *                               All rights reserved
 *  ============================================================================
 *  Notice: All Rights Reserved.
 *  This material contains the trade secrets and confidential information of
 *  Pfizer Inc., which embody substantial creative effort, ideas and
 *  expressions. No part of this material may be reproduced or transmitted
 *  in any form or by any means, electronic, mechanical, optical or otherwise,
 *  including photocopying and recording, or in connection with any information
 *  storage or retrieval system, without written permission from:
 *
 *  Hospira, a Pfizer company
 *  13520 Evening Creek Dr., Suite 200
 *  San Diego, CA 92128
 *  www.hospira.com
 *******************************************************************************/
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
