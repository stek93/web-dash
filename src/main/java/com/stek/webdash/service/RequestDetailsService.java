package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;

import com.stek.webdash.model.domain.RequestDetails;
import com.stek.webdash.model.ui.RequestDetailsDto;

public interface RequestDetailsService {

	RequestDetails saveRequestDetails(RequestDetails requestDetails);

	List<RequestDetailsDto> findAllRequestDetails();

	Optional<RequestDetailsDto> findRequestDetailsById(Long id);

	void deleteRequestDetails(RequestDetails requestDetails);

}
