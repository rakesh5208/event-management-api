package com.learningwithrakesh.EventManagement.dto;

public class ErrorResponse {
	private String message;
	private String path;
	private int status;
	private String type;

	private static final ErrorResponse er = new ErrorResponse();

	public static ErrorResponse GetInstance() {
		return er;
	}

	private ErrorResponse() {
	}

	public String getMessage() {
		return message;
	}

	public ErrorResponse withMessage(String message) {
		this.message = message;
		return this;
	}

	public String getPath() {
		return path;
	}

	public ErrorResponse withPath(String path) {
		this.path = path;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public ErrorResponse withStatus(int status) {
		this.status = status;
		return this;
	}

	public ErrorResponse withType(String type) {
		this.type = type;
		return this;
	}

	public String getType() {
		return this.type;
	}
}
