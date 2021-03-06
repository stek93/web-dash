package com.stek.webdash.service;

import java.util.Optional;
import java.util.Set;

import com.stek.webdash.model.domain.EndpointMapping;

public interface EndpointMappingService {

	EndpointMapping saveEndpointMapping(EndpointMapping endpointMapping);

	Set<EndpointMapping> findAllEndpointMappings();

	Optional<EndpointMapping> findEndpointMappingById(Long id);

	void deleteEndpointMapping(EndpointMapping endpointMapping);

	void saveAllEndpointMappings(Set<EndpointMapping> newEndpointMappingsSet);

	Optional<EndpointMapping> findEndpointMappingByMethodAndURI(String httpMethod, String requestUri);

}
