package com.learningwithrakesh.EventManagement.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Role extends BaseDomain {
	private Set<Privilege> privileges = new HashSet<>();

	public Role() {
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public String toString() {
		return "Role [privileges=" + privileges + "]";
	}

}
