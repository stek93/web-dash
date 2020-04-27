package com.stek.webdash.util.processor;

import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.stek.webdash.controller.RequestsInspectorController;
import com.stek.webdash.model.ui.RequestDetailsDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RequestDetailsDtoProcessor implements RepresentationModelProcessor<RequestDetailsDto> {

	private final RequestsInspectorController controller = methodOn(RequestsInspectorController.class);

	@Override
	public RequestDetailsDto process(RequestDetailsDto model) {
		final String LINK_TO_ALL = "all";

		model.add(linkTo(controller.findRequestDetailsById(model.getId())).withSelfRel());
		model.add(linkTo(controller.findAllRequestDetails()).withRel(LINK_TO_ALL));

		return model;
	}
}
