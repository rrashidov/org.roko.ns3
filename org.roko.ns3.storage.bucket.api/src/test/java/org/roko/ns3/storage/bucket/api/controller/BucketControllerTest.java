package org.roko.ns3.storage.bucket.api.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.storage.bucket.api.service.BucketService;
import org.springframework.web.multipart.MultipartFile;

public class BucketControllerTest {

	private static final String TEST_FILE_NAME = "test_file_name";

	@Mock
	private InputStream inputStreamMock;
	
	@Mock
	private BucketService bucketServiceMock;

	@Mock
	private MultipartFile fileMock;

	private BucketController bucketController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		bucketController = new BucketController(bucketServiceMock);
	}
	
	@Test
	public void uploadWorksProperly() throws IOException {
		when(fileMock.getInputStream()).thenReturn(inputStreamMock);
		
		bucketController.upload(TEST_FILE_NAME, fileMock);
		
		verify(bucketServiceMock).create(TEST_FILE_NAME, inputStreamMock);
	}
}
