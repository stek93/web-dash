package com.stek.webdash.util.mapper;

import org.mapstruct.Mapper;

import com.stek.webdash.model.domain.RequestDetails;
import com.stek.webdash.model.ui.RequestDetailsDto;

@Mapper(componentModel = "spring")
public interface RequestDetailsMapper {

	RequestDetailsDto entityToDto(RequestDetails requestDetails);

	RequestDetails dtoToEntity(RequestDetailsDto requestDetailsDto);

}
