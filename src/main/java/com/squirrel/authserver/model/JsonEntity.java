package com.squirrel.authserver.model;

import java.io.Serializable;

public class JsonEntity<T> implements Serializable {

	private static final long serialVersionUID = 615304965229311371L;
	private Integer status = 200;
	private String message;
	private String requestId;
	private T data;

	@SuppressWarnings("rawtypes")
	public JsonEntity setStatus(Integer status) {
		this.status = status;
		return this;
	}

	@SuppressWarnings("rawtypes")
	public JsonEntity setMessage(String message) {
		this.message = message;
		return this;
	}

	@SuppressWarnings("rawtypes")
	public JsonEntity setRequestId(String requestId) {
		this.requestId = requestId;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public JsonEntity setData(T data) {
		this.data = data;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getRequestId() {
		return requestId;
	}

	public T getData() {
		return data;
	}

	@Override
	public String toString() {
		return "{\"status\":\"" + status + "\", \"message\":\"" + message + "\", \"requestId\":\"" + requestId
				+ "\", \"data\":\"" + data + "\"}";
	}
}
