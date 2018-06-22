package com.learningwithrakesh.EventManagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
@SuppressWarnings("javadoc")
public class User extends BaseDomain {

	private String username;

	private String name;

	private String email;

	@Column(length = 10)
	private String phone;

	private boolean locked;

	private boolean expired;

	private boolean enabled;

	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", email=" + email + ", phone=" + phone + ", locked="
				+ locked + ", expired=" + expired + ", enabled=" + enabled + "]";
	}

}
