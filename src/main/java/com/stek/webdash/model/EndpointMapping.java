package com.stek.webdash.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "endpoint_mappings")
@Getter
@Setter
@NoArgsConstructor
public class EndpointMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String requestUri;

	private String httpMethod;

	@OneToMany(mappedBy = "endpointHit")
	private List<RequestDetails> requests;

	public EndpointMapping(String requestUri, String httpMethod) {
		this.requestUri = requestUri;
		this.httpMethod = httpMethod;
		this.requests = new ArrayList<>();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EndpointMapping that = (EndpointMapping) o;
		return requestUri.equals(that.requestUri) && httpMethod.equals(that.httpMethod);
	}

	@Override
	public int hashCode() {
		return Objects.hash(requestUri, httpMethod);
	}
}
