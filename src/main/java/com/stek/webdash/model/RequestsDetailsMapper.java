package com.stek.webdash.model;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.stek.webdash.model.response.IpApiResponse;

@Mapper(componentModel = "spring")
public interface RequestsDetailsMapper {

	@Mappings({ @Mapping(source = "query", target = "ipAddress"), @Mapping(source = "lat", target = "latitude"), @Mapping(source = "lon", target = "longitude") })
	RequestHost toEntity(IpApiResponse ipApiResponse);

}
