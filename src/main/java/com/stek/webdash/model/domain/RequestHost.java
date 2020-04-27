package com.stek.webdash.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "request_hosts")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class RequestHost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
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

	@OneToMany(mappedBy = "requestHost")
	private List<RequestDetails> requestDetails;

}
