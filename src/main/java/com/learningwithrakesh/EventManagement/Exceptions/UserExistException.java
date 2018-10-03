package com.learningwithrakesh.EventManagement.Exceptions;

public class UserExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserExistException(String msg) {
		super(msg);
	}

	public UserExistException(String msg, Throwable t) {
		super(msg, t);
	}

}
