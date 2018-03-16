package com.squirrel.authserver.constants;

public enum ResultStatus {

	SUCCESS(200, "成功"),
	USERNAME_OR_PASSWORD_ERROR(10001, "Username or password error!"),
	USER_NOT_FOUND(10002, "User could not find!"),
	USER_NOT_LOGIN(10003, "Please login to authenticate user info!");
	
	private int code;
	private String message;

	private ResultStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
