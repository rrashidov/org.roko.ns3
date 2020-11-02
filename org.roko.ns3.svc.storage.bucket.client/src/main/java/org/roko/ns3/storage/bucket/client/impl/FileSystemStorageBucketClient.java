package org.roko.ns3.storage.bucket.client.impl;

import java.util.List;

import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FileSystemStorageBucketClient implements StorageBucketClient {

	private static final String CREATE_URL_TEMPLATE = "%s/api/v1/files/%s";

	private String id;
	private String serviceUrl;
	private RestTemplate restTemplate;

	public FileSystemStorageBucketClient(String id, String serviceURL) {
		this(id, serviceURL, new RestTemplate());
	}

	public FileSystemStorageBucketClient(String id, String serviceUrl, RestTemplate restTemplate) {
		this.id = id;
		this.serviceUrl = serviceUrl;
		this.restTemplate = restTemplate;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void create(String name, byte[] data) {
	    MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	    map.add("name", name);
	    map.add("filename", name);
	    
	    ByteArrayResource contentsAsResource = new ByteArrayResource(data){
	        @Override
	        public String getFilename() {
	            return "filename";
	        }
	    };
	    map.add("file", contentsAsResource);

	    restTemplate.postForObject(createUrl(name), map, String.class);
	}

	@Override
	public byte[] read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> list() {
		// TODO Auto-generated method stub
		return null;
	}

	private String createUrl(String name) {
		return String.format(CREATE_URL_TEMPLATE, serviceUrl, name);
	}

	/*
	private String CALCULATE_FUNCTION_URL_TEMPLATE = "%s/api/v1/calculate";
	
	private String calculateFunctionUrl;
	
	private RestTemplate restTemplate;

	public FileSystemStorageBucketClient(String serviceUrl) {
		this(serviceUrl, new RestTemplate());
	}
	
	FileSystemStorageBucketClient(String serviceUrl, RestTemplate restTemplate) {
		this.calculateFunctionUrl = String.format(CALCULATE_FUNCTION_URL_TEMPLATE, serviceUrl);
		this.restTemplate = restTemplate;
	}

	@Override
	public String calculate(byte[] data) {
	    MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	    map.add("name", "filename");
	    map.add("filename", "filename");
	    
	    ByteArrayResource contentsAsResource = new ByteArrayResource(data){
	        @Override
	        public String getFilename() {
	            return "filename";
	        }
	    };
	    map.add("file", contentsAsResource);

	    return restTemplate.postForObject(calculateFunctionUrl, map, String.class);
	}
	*/
}
