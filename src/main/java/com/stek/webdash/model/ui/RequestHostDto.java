package com.stek.webdash.model.ui;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;

@Data
public class RequestHostDto extends RepresentationModel<RequestHostDto> {

	private Long id;

	private String ipAddress;

	private String continent;

	private String continentCode;

	private String country;

	private String countryCode;

	private Double latitude;

	private Double longitude;

	private String timezone;

	private String currency;

	private String isp;

	private List<RequestDetailsDto> requestDetails;

}
