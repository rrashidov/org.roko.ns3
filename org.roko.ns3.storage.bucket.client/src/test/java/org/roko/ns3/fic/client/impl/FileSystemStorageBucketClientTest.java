package org.roko.ns3.fic.client.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.eq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.roko.ns3.storage.bucket.client.impl.FileSystemStorageBucketClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class FileSystemStorageBucketClientTest {
	
	private static final String TEST_CLIENT_ID = "1";

	private static final String CREATE_URL_TEMPLATE = "%s/api/v1/files/%s";

	private static final String FILE_REQUEST_PARAM_NAME = "file";
	private static final String FILENAME_REQUEST_PARAM_NAME = "filename";
	private static final String NAME_REQUEST_PARAM_NAME = "name";

	private static final String TEST_BUCKET_SERVICE_URL = "test_bucket_service_url";

	private static final String TEST_FILE_NAME = "test_file_name";

	private static final byte[] TEST_DATA = new byte[] {};
	
	@Mock
	private RestTemplate restTemplateMock;
	
	@Captor
	private ArgumentCaptor<MultiValueMap<String, Object>> sentMapArgumentCaptor;
	
	private StorageBucketClient client;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		client = new FileSystemStorageBucketClient(TEST_CLIENT_ID, TEST_BUCKET_SERVICE_URL, restTemplateMock);
	}
	
	@Test
	public void createWorksProperly() {
		client.create(TEST_FILE_NAME, TEST_DATA);
		
		String fileUploadUrlExpectedToBeCalled = String.format(CREATE_URL_TEMPLATE, TEST_BUCKET_SERVICE_URL, TEST_FILE_NAME);
		
		verify(restTemplateMock).postForObject(eq(fileUploadUrlExpectedToBeCalled), sentMapArgumentCaptor.capture(), eq(String.class));
		
		MultiValueMap<String, Object> sentMap = sentMapArgumentCaptor.getValue();
		
		assertTrue(sentMap.containsKey(FILE_REQUEST_PARAM_NAME), "data should be sent as file request param");
		assertTrue(sentMap.containsKey(FILENAME_REQUEST_PARAM_NAME), "data should be sent as filename request param");
		assertTrue(sentMap.containsKey(NAME_REQUEST_PARAM_NAME), "data should be sent as name request param");
		
		List<Object> sentFiles = sentMap.get(FILE_REQUEST_PARAM_NAME);
		
		assertEquals(1, sentFiles.size(), "Only one file should be sent in the request");
		
		ByteArrayResource sentFile = (ByteArrayResource) sentFiles.get(0);
		assertEquals(TEST_DATA, sentFile.getByteArray(), "bytes sent should be the same as the ones passed as param");
	}
}
