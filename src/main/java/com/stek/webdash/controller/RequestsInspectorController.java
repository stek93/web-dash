package com.stek.webdash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stek.webdash.exception.RequestDetailsNotFoundException;
import com.stek.webdash.exception.RequestHostNotFoundException;
import com.stek.webdash.model.ui.RequestDetailsDto;
import com.stek.webdash.model.ui.RequestHostDto;
import com.stek.webdash.service.RequestDetailsService;
import com.stek.webdash.service.RequestHostService;

@RestController
@RequestMapping(path = "/requests")
public class RequestsInspectorController {

	private final RequestHostService requestHostService;

	private final RequestDetailsService requestDetailsService;

	@Autowired
	public RequestsInspectorController(RequestHostService requestHostService, RequestDetailsService requestDetailsService) {
		this.requestHostService = requestHostService;
		this.requestDetailsService = requestDetailsService;
	}

	@GetMapping
	public ResponseEntity<List<RequestHostDto>> findAllRequests() {
		return ResponseEntity.ok(requestHostService.findAllRequestHosts());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<RequestHostDto> findRequestById(@PathVariable Long id) {
		return requestHostService.findRequestHostById(id).map(ResponseEntity::ok).orElseThrow(() -> new RequestHostNotFoundException(id));
	}

	@GetMapping(path = "/ip-address/{ipAddress}")
	public ResponseEntity<RequestHostDto> findRequestByIpAddress(@PathVariable String ipAddress) {
		return requestHostService.findByIpAddress(ipAddress).map(request -> ResponseEntity.ok(requestHostService.getMapper().entityToDto(request)))
				.orElseThrow(() -> new RequestHostNotFoundException(ipAddress));
	}

	@GetMapping(path = "/details")
	public ResponseEntity<List<RequestDetailsDto>> findAllRequestDetails() {
		return ResponseEntity.ok(requestDetailsService.findAllRequestDetails());
	}

	@GetMapping(path = "/details/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RequestDetailsDto> findRequestDetailsById(@PathVariable Long id) {
		return requestDetailsService.findRequestDetailsById(id).map(ResponseEntity::ok).orElseThrow(() -> new RequestDetailsNotFoundException(id));
	}

}
