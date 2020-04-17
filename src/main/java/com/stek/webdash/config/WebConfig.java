package com.stek.webdash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final String ANT_PATTERN_REQUESTS_LOGGER = "/requests-logger/**";

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
