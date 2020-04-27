package com.stek.webdash.model.ui;

import lombok.Data;

@Data
public class IpApiResponse {

	private String query;

	private String continent;

	private String continentCode;

	private String country;

	private String countryCode;

	private Double lat;

	private Double lon;

	private String timezone;

	private String currency;

	private String isp;

	private boolean mobile;

	private boolean proxy;

	private boolean hosting;

}
