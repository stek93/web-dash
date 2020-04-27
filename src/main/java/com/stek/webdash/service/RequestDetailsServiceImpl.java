package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stek.webdash.model.domain.RequestDetails;
import com.stek.webdash.model.ui.RequestDetailsDto;
import com.stek.webdash.service.repository.RequestDetailsRepository;
import com.stek.webdash.util.mapper.RequestDetailsMapper;

@Service
public class RequestDetailsServiceImpl implements RequestDetailsService {

	private final RequestDetailsRepository requestDetailsRepository;

	private final RequestDetailsMapper requestDetailsMapper;

	@Autowired
	public RequestDetailsServiceImpl(RequestDetailsRepository requestDetailsRepository, RequestDetailsMapper requestDetailsMapper) {
		this.requestDetailsRepository = requestDetailsRepository;
		this.requestDetailsMapper = requestDetailsMapper;
	}

	@Override
	public RequestDetails saveRequestDetails(RequestDetails requestDetails) {
		return requestDetailsRepository.save(requestDetails);
	}

	@Override
	public List<RequestDetailsDto> findAllRequestDetails() {
		return requestDetailsRepository.findAll().stream().map(requestDetailsMapper::entityToDto).collect(Collectors.toList());
	}

	@Override
	public Optional<RequestDetailsDto> findRequestDetailsById(Long id) {
		return requestDetailsRepository.findById(id).map(requestDetailsMapper::entityToDto);
	}

	@Override
	public void deleteRequestDetails(RequestDetails requestDetails) {
		requestDetailsRepository.delete(requestDetails);
	}
}
