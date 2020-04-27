package com.stek.webdash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	// TODO: read context path from .properties file
	private final String ANT_PATTERN_REQUESTS_LOGGER = "/inspector/requests/**";

	private final RequestInfoInterceptor requestInfoInterceptor;

	@Autowired
	public WebConfig(RequestInfoInterceptor requestInfoInterceptor) {
		this.requestInfoInterceptor = requestInfoInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInfoInterceptor).excludePathPatterns(ANT_PATTERN_REQUESTS_LOGGER);
	}
}
