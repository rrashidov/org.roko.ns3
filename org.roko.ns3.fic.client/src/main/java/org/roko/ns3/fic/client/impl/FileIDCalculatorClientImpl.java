package org.roko.ns3.fic.client.impl;

import org.roko.ns3.fic.client.api.FileIDCalculatorClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FileIDCalculatorClientImpl implements FileIDCalculatorClient {

	private String CALCULATE_FUNCTION_URL_TEMPLATE = "%s/api/v1/calculate";
	
	private String calculateFunctionUrl;
	
	private RestTemplate restTemplate;

	public FileIDCalculatorClientImpl(String serviceUrl) {
		this(serviceUrl, new RestTemplate());
	}
	
	FileIDCalculatorClientImpl(String serviceUrl, RestTemplate restTemplate) {
		this.calculateFunctionUrl = String.format(CALCULATE_FUNCTION_URL_TEMPLATE, serviceUrl);
		this.restTemplate = restTemplate;
	}

	@Override
	public String calculate(byte[] data) {
	    MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	    ByteArrayResource contentsAsResource = new ByteArrayResource(data);
	    map.add("file", contentsAsResource);

	    return restTemplate.postForObject(calculateFunctionUrl, map, String.class);
	}
}
