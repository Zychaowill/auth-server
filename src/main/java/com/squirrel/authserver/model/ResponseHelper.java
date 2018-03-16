package com.squirrel.authserver.model;

import com.squirrel.authserver.constants.ResultStatus;

public class ResponseHelper {

	public static <T> JsonEntity<T> createInstance(T data) {
		JsonEntity<T> response = new JsonEntity<>();
		response.setData(data);
		response.setRequestId(null);
		return response;
	}
	
	public static <T> JsonEntity<T> of(T data) {
		return createInstance(data);
	}
	
	public static <T> JsonEntity<T> ofNothing() {
		return createInstance(null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JsonEntity<T> withMessage(String message) {
		return ofNothing().setMessage(message);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JsonEntity<T> withErrorMessage(String message) {
		return ofNothing().setMessage(message).setStatus(500);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> JsonEntity<T> withErrorMessage(ResultStatus status) {
		return ofNothing().setMessage(status.getMessage()).setStatus(status.getCode());
	}
	
	public static <T> JsonEntity<T> fromJson(String json) {
		return null;
	}
}
