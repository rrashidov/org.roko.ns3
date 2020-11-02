package org.roko.ns3.org.roko.ns3.catalog.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryDTO;
import org.roko.ns3.org.roko.ns3.catalog.api.service.PathParseException;
import org.roko.ns3.org.roko.ns3.catalog.api.service.PathParseService;
import org.roko.ns3.org.roko.ns3.catalog.api.service.StorageEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
@RequestMapping(APIController.URL_MAPPING_ROOT)
public class APIController {

	static final String URL_MAPPING_ROOT = "/api/v1";
	
	private PathParseService pathParseService;
	private StorageEntryService storageEntryService;

	@Autowired
	public APIController(PathParseService pathParseService, StorageEntryService storageEntryService) {
		this.pathParseService = pathParseService;
		this.storageEntryService = storageEntryService;
	}

	@GetMapping
	@RequestMapping("/**")
	public String upload(HttpServletRequest request) {

		String requestPath = (String) request.getAttribute(
		        HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		String catalogPath = requestPath.replace(URL_MAPPING_ROOT, "");
		
		try {
			List<StorageEntryDTO> storageEntryDTOs = pathParseService.parse(catalogPath);
			
			storageEntryService.persistDTOs(storageEntryDTOs);
		} catch (PathParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(requestPath);
		
		return requestPath;
	}
}
