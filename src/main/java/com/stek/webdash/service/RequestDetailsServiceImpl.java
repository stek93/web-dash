package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stek.webdash.model.RequestDetails;
import com.stek.webdash.service.repository.RequestDetailsRepository;

@Service
public class RequestDetailsServiceImpl implements RequestDetailsService {

	private final RequestDetailsRepository requestDetailsRepository;

	@Autowired
	public RequestDetailsServiceImpl(RequestDetailsRepository requestDetailsRepository) {
		this.requestDetailsRepository = requestDetailsRepository;
	}

	@Override
	public RequestDetails saveRequestDetails(RequestDetails requestDetails) {
		return requestDetailsRepository.save(requestDetails);
	}

	@Override
	public List<RequestDetails> findAllRequestDetails() {
		return requestDetailsRepository.findAll();
	}

	@Override
	public Optional<RequestDetails> findRequestDetailsById(Integer id) {
		return requestDetailsRepository.findById(id);
	}

	@Override
	public void deleteRequestDetails(RequestDetails requestDetails) {
		requestDetailsRepository.delete(requestDetails);
	}
}
