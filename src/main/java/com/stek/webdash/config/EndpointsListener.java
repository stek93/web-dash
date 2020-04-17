package com.stek.webdash.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.stek.webdash.model.EndpointMapping;
import com.stek.webdash.service.EndpointMappingService;

@Component
public class EndpointsListener implements ApplicationListener<ContextRefreshedEvent> {

	private final EndpointMappingService endpointMappingService;

	private final Set<EndpointMapping> endpointMappings;

	@Autowired
	public EndpointsListener(EndpointMappingService endpointMappingService) {
		this.endpointMappingService = endpointMappingService;
		this.endpointMappings = new HashSet<>();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
			// TODO: adjust this method to handle possibility of having multiple patterns with different http verbs even though spring usually has one pattern for one http verb
			// FIXME: find the way to make this more performing
			requestMappingInfo.getPatternsCondition().getPatterns().forEach(pattern -> {
				requestMappingInfo.getMethodsCondition().getMethods().forEach(requestMethod -> {
					endpointMappings.add(new EndpointMapping(pattern, requestMethod.toString()));
				});
			});
		});
		endpointMappingService.saveAllEndpointMappings(endpointMappings);
	}
}
