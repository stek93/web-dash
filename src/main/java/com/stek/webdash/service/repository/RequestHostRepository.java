package com.stek.webdash.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stek.webdash.model.domain.RequestHost;

@Repository
public interface RequestHostRepository extends JpaRepository<RequestHost, Long> {

	Optional<RequestHost> findByIpAddress(String ipAddress);

}
