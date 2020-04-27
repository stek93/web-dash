package com.stek.webdash.model.ui;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class RequestDetailsDto extends RepresentationModel<RequestDetailsDto> {

	private Long id;

	private String browser;

	private String browserType;

	private String browserMajorVersion;

	private String deviceType;

	private String platform;

	private String platformVersion;

	private boolean mobile;

	private boolean proxy;

	private boolean hosting;

	private String requestEndpoint;

	private String requestMethod;

	private boolean isError;

	private String errorMessage;

	private int responseCode;

	private String response;

	private EndpointMappingDto endpointHit;

}
