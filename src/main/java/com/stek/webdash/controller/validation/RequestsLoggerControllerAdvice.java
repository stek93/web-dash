package com.stek.webdash.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stek.webdash.core.RequestInspector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RequestsLoggerControllerAdvice extends ResponseEntityExceptionHandler {

	private final RequestInspector requestInspector;

	@Autowired
	public RequestsLoggerControllerAdvice(RequestInspector requestInspector) {
		this.requestInspector = requestInspector;
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		requestInspector.interceptClientRequest((ServletWebRequest) request, status, ex);
		return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
	}
}
