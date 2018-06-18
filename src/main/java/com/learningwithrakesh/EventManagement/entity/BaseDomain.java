package com.learningwithrakesh.EventManagement.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 */
@SuppressWarnings("javadoc")
@MappedSuperclass
public class BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long whenCreated;
	private Long whenLastUpdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(Long whenCreated) {
		this.whenCreated = whenCreated;
	}

	public Long getWhenLastUpdated() {
		return whenLastUpdated;
	}

	public void setWhenLastUpdated(Long whenLastUpdated) {
		this.whenLastUpdated = whenLastUpdated;
	}

}
