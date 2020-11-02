package org.roko.ns3.org.roko.ns3.storage.client.impl;

import org.roko.ns3.org.roko.ns3.storage.client.api.StorageClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class StorageClientImpl implements StorageClient {
	
	private static final String UPLOAD_URL_TEMPLATE = "%s/api/v1/files";

	private String url;
	private RestTemplate restTemplate;

	public StorageClientImpl(String url) {
		this(url, new RestTemplate());
	}

	public StorageClientImpl(String url, RestTemplate restTemplate) {
		this.url = url;
		this.restTemplate = restTemplate;
	}

	@Override
	public String upload(byte[] data) {
	    MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

	    map.add("name", "name");
	    map.add("filename", "filename");
	    
	    ByteArrayResource contentsAsResource = new ByteArrayResource(data){
	        @Override
	        public String getFilename() {
	            return "filename";
	        }
	    };
	    map.add("file", contentsAsResource);

	    return restTemplate.postForObject(getUploadUrl(), map, String.class);
	}

	private String getUploadUrl() {
		return String.format(UPLOAD_URL_TEMPLATE, url);
	}
}
