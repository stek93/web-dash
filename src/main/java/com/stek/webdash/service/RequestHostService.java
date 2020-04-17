package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.stek.webdash.model.RequestHost;
import com.stek.webdash.model.response.IpApiResponse;

public interface RequestHostService {

	RequestHost saveRequestHost(RequestHost requestHost);

	List<RequestHost> findAllRequestHosts();

	Optional<RequestHost> findRequestHostById(Integer id);

	void deleteRequestHost(RequestHost requestHost);

	CompletableFuture<IpApiResponse> getAsyncIpAddressDetails(String ipAddress);

	Optional<RequestHost> findByIpAddress(String ipAddress);

	RequestHost ipApiResponseToRequestHost(IpApiResponse ipApiResponse);
}
