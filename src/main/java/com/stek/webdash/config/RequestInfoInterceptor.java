package com.stek.webdash.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;

import com.stek.webdash.core.RequestInspector;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RequestInfoInterceptor implements HandlerInterceptor {

	private final Environment environment;

	private final RequestInspector requestInspector;

	@Autowired
	public RequestInfoInterceptor(Environment environment, RequestInspector requestInspector) {
		this.environment = environment;
		this.requestInspector = requestInspector;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String contextPath = environment.getProperty("server.servlet.context-path");
		// FIXME: We want to avoid to persist redirects to error page. Have to find more flexible and meaningful approach to handle this because we want to persist some of those requests but not all of them
		if (!request.getRequestURI().equals(contextPath + "/error")) {
			requestInspector.interceptClientRequest(request, response, ex);
		}
	}
}
