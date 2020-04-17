package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stek.webdash.model.RequestHost;
import com.stek.webdash.model.RequestsDetailsMapper;
import com.stek.webdash.model.response.IpApiResponse;
import com.stek.webdash.service.repository.RequestHostRepository;

@Service
public class RequestHostServiceImpl implements RequestHostService {

	private static final String IP_API_URL = "http://ip-api.com/json/%s?fields=%s";
	private static final String[] IP_API_REQUEST_FIELDS = { "query", "continent", "continentCode", "country", "countryCode", "lat", "lon", "timezone", "currency", "isp", "mobile", "proxy",
			"hosting" };

	private final RequestHostRepository requestHostRepository;
	private final RestTemplate restTemplate;
	private final RequestsDetailsMapper requestsDetailsMapper;

	@Autowired
	public RequestHostServiceImpl(RequestHostRepository requestHostRepository, RestTemplateBuilder restTemplateBuilder, RequestsDetailsMapper requestsDetailsMapper) {
		this.requestHostRepository = requestHostRepository;
		this.restTemplate = restTemplateBuilder.build();
		this.requestsDetailsMapper = requestsDetailsMapper;
	}

	@Override
	public RequestHost saveRequestHost(RequestHost requestHost) {
		return requestHostRepository.save(requestHost);
	}

	@Override
	public List<RequestHost> findAllRequestHosts() {
		return requestHostRepository.findAll();
	}

	@Override
	public Optional<RequestHost> findRequestHostById(Integer id) {
		return requestHostRepository.findById(id);
	}

	@Override
	public void deleteRequestHost(RequestHost requestHost) {
		requestHostRepository.delete(requestHost);
	}

	@Async
	@Override
	public CompletableFuture<IpApiResponse> getAsyncIpAddressDetails(String ipAddress) {
		String fields = String.join(",", IP_API_REQUEST_FIELDS);
		String url = String.format(IP_API_URL, ipAddress, fields);

		return CompletableFuture.completedFuture(restTemplate.getForObject(url, IpApiResponse.class));
	}

	@Override
	public Optional<RequestHost> findByIpAddress(String ipAddress) {
		return requestHostRepository.findByIpAddress(ipAddress);
	}

	@Override
	public RequestHost ipApiResponseToRequestHost(IpApiResponse ipApiResponse) {
		return requestsDetailsMapper.toEntity(ipApiResponse);
	}
}
