package com.stek.webdash.exception;

public class RequestDetailsNotFoundException extends RuntimeException {

	public RequestDetailsNotFoundException(Long id) {
		super(String.format("Request details not found for parameters {id=%d}", id));
	}
}
