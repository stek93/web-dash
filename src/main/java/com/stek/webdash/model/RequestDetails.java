package com.stek.webdash.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "request_details")
@Getter
@Setter
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class RequestDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

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

	@ManyToOne
	private RequestHost requestHost;

	@ManyToOne
	private EndpointMapping endpointHit;

	public RequestDetails(String browser, String browserType, String browserMajorVersion, String deviceType, String platform, String platformVersion) {
		this.browser = browser;
		this.browserType = browserType;
		this.browserMajorVersion = browserMajorVersion;
		this.deviceType = deviceType;
		this.platform = platform;
		this.platformVersion = platformVersion;
	}
}
