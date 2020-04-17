package com.stek.webdash.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stek.webdash.model.EndpointMapping;
import com.stek.webdash.service.repository.EndpointMappingRepository;

@Service
public class EndpointMappingServiceImpl implements EndpointMappingService {

	private final EndpointMappingRepository endpointMappingRepository;

	@Autowired
	public EndpointMappingServiceImpl(EndpointMappingRepository endpointMappingRepository) {
		this.endpointMappingRepository = endpointMappingRepository;
	}

	@Override
	public EndpointMapping saveEndpointMapping(EndpointMapping endpointMapping) {
		return endpointMappingRepository.save(endpointMapping);
	}

	@Override
	public Set<EndpointMapping> findAllEndpointMappings() {
		return new HashSet<>(endpointMappingRepository.findAll());
	}

	@Override
	public Optional<EndpointMapping> findEndpointMappingById(Integer id) {
		return endpointMappingRepository.findById(id);
	}

	@Override
	public void deleteEndpointMapping(EndpointMapping endpointMapping) {
		endpointMappingRepository.delete(endpointMapping);
	}

	@Override
	public void saveAllEndpointMappings(Set<EndpointMapping> newEndpointMappingsSet) {
		Set<EndpointMapping> allEndpointMappings = findAllEndpointMappings();
		newEndpointMappingsSet.forEach(endpointMapping -> {
			if (!allEndpointMappings.contains(endpointMapping))
				saveEndpointMapping(endpointMapping);
		});
	}

	@Override
	public Optional<EndpointMapping> findEndpointMappingByMethodAndURI(String httpMethod, String requestUri) {
		return endpointMappingRepository.findByHttpMethodAndRequestUri(httpMethod, requestUri);
	}
}
