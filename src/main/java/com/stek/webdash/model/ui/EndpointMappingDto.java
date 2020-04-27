package com.stek.webdash.model.ui;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class EndpointMappingDto extends RepresentationModel<EndpointMappingDto> {

	private Long id;

	private String requestUri;

	private String httpMethod;

}