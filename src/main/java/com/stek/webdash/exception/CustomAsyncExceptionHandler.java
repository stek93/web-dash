package com.stek.webdash.exception;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
		log.error("Exception: " + throwable.getMessage(), throwable.getCause());
		log.error(Arrays.toString(throwable.getStackTrace()));

		log.error("Method name: " + method.getName());
		for (Object param : objects) {
			log.error("Parameters which invoked failing method: " + param.toString());
		}
	}
}
