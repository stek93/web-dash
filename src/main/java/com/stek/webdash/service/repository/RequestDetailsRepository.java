package com.stek.webdash.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stek.webdash.model.domain.RequestDetails;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long> {
}
