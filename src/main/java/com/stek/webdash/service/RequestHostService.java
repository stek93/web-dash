package com.stek.webdash.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.stek.webdash.model.domain.RequestHost;
import com.stek.webdash.model.ui.IpApiResponse;
import com.stek.webdash.model.ui.RequestHostDto;
import com.stek.webdash.util.mapper.RequestHostsMapper;

public interface RequestHostService {

	RequestHost saveRequestHost(RequestHost requestHost);

	List<RequestHostDto> findAllRequestHosts();

	Optional<RequestHostDto> findRequestHostById(Long id);

	void deleteRequestHost(RequestHost requestHost);

	CompletableFuture<IpApiResponse> getAsyncIpAddressDetails(String ipAddress);

	Optional<RequestHost> findByIpAddress(String ipAddress);

	RequestHost ipApiResponseToRequestHost(IpApiResponse ipApiResponse);

	RequestHostsMapper getMapper();

}
