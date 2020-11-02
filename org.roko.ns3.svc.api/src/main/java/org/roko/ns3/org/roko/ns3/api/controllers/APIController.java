package org.roko.ns3.org.roko.ns3.api.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping(APIController.URL_MAPPINT_ROOT)
public class APIController {

	static final String URL_MAPPINT_ROOT = "/api/v1/";

	@GetMapping
	@RequestMapping("/**")
	public String upload(HttpServletRequest request) {

		String requestPath = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

		System.out.println(requestPath);
		
		return requestPath;
	}
}
