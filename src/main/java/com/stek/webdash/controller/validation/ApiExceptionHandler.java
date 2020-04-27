package com.stek.webdash.controller.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.stek.webdash.core.RequestInspector;
import com.stek.webdash.exception.RequestHostNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private final RequestInspector requestInspector;

	@Autowired
	public ApiExceptionHandler(RequestInspector requestInspector) {
		this.requestInspector = requestInspector;
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		requestInspector.interceptClientRequest((ServletWebRequest) request, status, ex);
		String message = "Method not supported";
		return buildResponseEntity(new ApiError(status, message, ex));
	}

	@ExceptionHandler(value = { RequestHostNotFoundException.class, RequestHostNotFoundException.class })
	public ResponseEntity<?> handleEntitiesNotFoundException(Exception ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex));
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
