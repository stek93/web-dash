package com.stek.webdash.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stek.webdash.model.EndpointMapping;

@Repository
public interface EndpointMappingRepository extends JpaRepository<EndpointMapping, Integer> {

	Optional<EndpointMapping> findByHttpMethodAndRequestUri(String httpMethod, String requestUri);

}
