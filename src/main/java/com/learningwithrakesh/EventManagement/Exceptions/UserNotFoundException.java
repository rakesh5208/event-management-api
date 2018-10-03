package com.learningwithrakesh.EventManagement.Exceptions;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	public UserNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
