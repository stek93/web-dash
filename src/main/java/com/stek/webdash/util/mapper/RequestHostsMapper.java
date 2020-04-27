package com.stek.webdash.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.stek.webdash.model.domain.RequestHost;
import com.stek.webdash.model.ui.IpApiResponse;
import com.stek.webdash.model.ui.RequestHostDto;

@Mapper(componentModel = "spring")
public interface RequestHostsMapper {

	@Mappings({ @Mapping(source = "query", target = "ipAddress"), @Mapping(source = "lat", target = "latitude"), @Mapping(source = "lon", target = "longitude") })
	RequestHost ipApiResponseToEntity(IpApiResponse ipApiResponse);

	RequestHostDto entityToDto(RequestHost requestHost);

	RequestHost dtoToEntity(RequestHostDto requestHostDto);
}
