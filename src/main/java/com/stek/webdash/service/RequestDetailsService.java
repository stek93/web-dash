package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;

import com.stek.webdash.model.RequestDetails;

public interface RequestDetailsService {

	RequestDetails saveRequestDetails(RequestDetails requestDetails);

	List<RequestDetails> findAllRequestDetails();

	Optional<RequestDetails> findRequestDetailsById(Integer id);

	void deleteRequestDetails(RequestDetails requestDetails);

}
