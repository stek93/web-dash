package com.stek.webdash.core;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.blueconic.browscap.Capabilities;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.stek.webdash.model.domain.EndpointMapping;
import com.stek.webdash.model.domain.RequestDetails;
import com.stek.webdash.model.domain.RequestHost;
import com.stek.webdash.service.EndpointMappingService;
import com.stek.webdash.service.RequestDetailsService;
import com.stek.webdash.service.RequestHostService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestInspector {

	private final String HEADER_USER_AGENT = "User-Agent";

	private final RequestHostService requestHostService;

	private final RequestDetailsService requestDetailsService;

	private final EndpointMappingService endpointMappingService;

	private final UserAgentParser userAgentParser;

	@Autowired
	public RequestInspector(RequestHostService requestHostService, RequestDetailsService requestDetailsService, EndpointMappingService endpointMappingService, UserAgentParser userAgentParser) {
		this.requestHostService = requestHostService;
		this.requestDetailsService = requestDetailsService;
		this.endpointMappingService = endpointMappingService;
		this.userAgentParser = userAgentParser;
	}

	public void interceptClientRequest(ServletWebRequest webRequest, HttpStatus httpStatus, Exception ex) {
		String userAgent = webRequest.getHeader(HEADER_USER_AGENT);
		String requestHttpMethod = String.valueOf(webRequest.getHttpMethod());
		String requestURI = webRequest.getRequest().getRequestURI();
		int responseCode = httpStatus.value();

		try {
			inspectRequest(userAgent, requestHttpMethod, requestURI, responseCode, ex);
		} catch (IOException e) {
			handleIOException(e);
		} catch (ParseException e) {
			handleParseException(e);
		}
	}

	public void interceptClientRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Exception ex) {
		String userAgent = httpServletRequest.getHeader(HEADER_USER_AGENT);
		String requestHttpMethod = httpServletRequest.getMethod();
		String requestURI = httpServletRequest.getRequestURI();
		int responseCode = httpServletResponse.getStatus();

		try {
			inspectRequest(userAgent, requestHttpMethod, requestURI, responseCode, ex);
		} catch (IOException e) {
			handleIOException(e);
		} catch (ParseException e) {
			handleParseException(e);
		}
	}

	private void inspectRequest(String userAgent, String requestHttpMethod, String requestURI, int responseCode, Exception ex) throws IOException, ParseException {
		final RequestDetails requestDetails;

		if (userAgent == null) {
			requestDetails = new RequestDetails();
		} else {
			final Capabilities capabilities = userAgentParser.parse(userAgent);

			requestDetails = new RequestDetails(capabilities.getBrowser(), capabilities.getBrowserType(), capabilities.getBrowserMajorVersion(), capabilities.getDeviceType(),
					capabilities.getPlatform(), capabilities.getPlatformVersion());
		}

		Optional<EndpointMapping> optionalEndpointMapping = endpointMappingService.findEndpointMappingByMethodAndURI(requestHttpMethod, requestURI);

		optionalEndpointMapping.ifPresentOrElse(requestDetails::setEndpointHit, () -> {
			requestDetails.setEndpointHit(null);
		});

		requestDetails.setError(HttpStatus.valueOf(responseCode).isError());
		requestDetails.setResponseCode(responseCode);
		requestDetails.setRequestEndpoint(requestURI);
		requestDetails.setRequestMethod(requestHttpMethod);

		if (ex != null) {
			requestDetails.setErrorMessage(ex.getMessage());
		} else if (requestDetails.isError()) {
			requestDetails.setErrorMessage(HttpStatus.valueOf(responseCode).getReasonPhrase());
		}

		// FIXME: Change this to be address of the incoming request
		String ipAddress = "188.124.214.139";

		Optional<RequestHost> optionalRequestHost = requestHostService.findByIpAddress(ipAddress);

		if (optionalRequestHost.isPresent()) {
			requestDetails.setRequestHost(optionalRequestHost.get());
			requestDetailsService.saveRequestDetails(requestDetails);
		} else {
			requestHostService.getAsyncIpAddressDetails(ipAddress).thenApply(ipApiResponse -> {
				RequestHost newRequestHost = requestHostService.saveRequestHost(requestHostService.ipApiResponseToRequestHost(ipApiResponse));

				requestDetails.setMobile(ipApiResponse.isMobile());
				requestDetails.setProxy(ipApiResponse.isProxy());
				requestDetails.setHosting(ipApiResponse.isHosting());
				requestDetails.setRequestHost(newRequestHost);
				requestDetailsService.saveRequestDetails(requestDetails);

				return newRequestHost;
			});
		}
	}

	private void handleIOException(IOException ex) {
		log.error("I/O operation failed when checking BrowsCap's CSV/ZIP file.", ex);
		log.error(ex.getMessage(), ex);
	}

	private void handleParseException(ParseException ex) {
		log.error("Regular expression in the BrowsCap'S CSV (library which checks browser/request details) cannot be parsed.");
		log.error(ex.getMessage(), ex);
	}

}
