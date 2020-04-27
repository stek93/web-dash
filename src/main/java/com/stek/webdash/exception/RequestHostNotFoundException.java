package com.stek.webdash.exception;

public class RequestHostNotFoundException extends RuntimeException {

	public RequestHostNotFoundException(Long id) {
		super(String.format("Request host not found for parameters {id=%d}", id));
	}

	public RequestHostNotFoundException(String ipAddress) {
		super(String.format("Request host not found for parameters {ipAddress=%s}", ipAddress));
	}
}
