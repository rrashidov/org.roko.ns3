package org.roko.ns3.fic.client.impl;

import static org.mockito.Mockito.verify;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.fic.client.api.FileIDCalculatorClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FileIDCalculatorClientImplTest {

	private static final String FILE_REQUEST_PARAM_NAME = "file";

	private static final String TEST_SERVICE_URL = "http://fic.service.url.com";

	private static final String EXPECTED_URL_TO_BE_CALLED = TEST_SERVICE_URL + "/api/v1/calculate";

	private static final byte[] TEST_BYTE_ARRAY = new byte[] {};
	
	@Captor
	private ArgumentCaptor<MultiValueMap<String, Object>> sentMapArgumentCaptor;
	
	@Mock
	private RestTemplate restTemplateMock;

	private FileIDCalculatorClient client;

	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		client = new FileIDCalculatorClientImpl(TEST_SERVICE_URL, restTemplateMock);
	}
	
	@Test
	public void calculateCallsProperEndpoint() {
		client.calculate(TEST_BYTE_ARRAY);
		
		verify(restTemplateMock).postForObject(eq(EXPECTED_URL_TO_BE_CALLED), sentMapArgumentCaptor.capture(), eq(String.class));
	}
	
	@Test
	public void calculateSendsProperRequest() {
		client.calculate(TEST_BYTE_ARRAY);
		
		verify(restTemplateMock).postForObject(eq(EXPECTED_URL_TO_BE_CALLED), sentMapArgumentCaptor.capture(), eq(String.class));
		
		verifySentRequest();
	}

	private void verifySentRequest() {
		MultiValueMap<String, Object> sentMap = sentMapArgumentCaptor.getValue();
		
		assertTrue(sentMap.containsKey(FILE_REQUEST_PARAM_NAME), "data should be sent as file request param");
		
		List<Object> sentFiles = sentMap.get(FILE_REQUEST_PARAM_NAME);
		
		assertEquals(1, sentFiles.size(), "Only one file should be sent in the request");
		
		ByteArrayResource sentFile = (ByteArrayResource) sentFiles.get(0);
		assertEquals(TEST_BYTE_ARRAY, sentFile.getByteArray(), "bytes sent should be the same as the ones passed as param");
	}
}
