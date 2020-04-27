package com.stek.webdash.util.processor;

import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import com.stek.webdash.controller.RequestsInspectorController;
import com.stek.webdash.model.ui.RequestHostDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RequestHostDtoProcessor implements RepresentationModelProcessor<RequestHostDto> {

	private final RequestsInspectorController controller = methodOn(RequestsInspectorController.class);

	@Override
	public RequestHostDto process(RequestHostDto model) {
		final String LINK_TO_ALL = "all";

		model.add(linkTo(controller.findRequestById(model.getId())).withSelfRel());
		model.add(linkTo(controller.findAllRequests()).withRel(LINK_TO_ALL));

		return model;
	}

}
