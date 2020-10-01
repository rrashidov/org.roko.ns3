package org.roko.ns3.storage.api;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.fic.client.api.FileIDCalculatorClient;
import org.roko.ns3.storage.api.controller.StoragerController;
import org.roko.ns3.storage.api.service.rules.BucketShardingServiceRule;
import org.springframework.web.multipart.MultipartFile;

public class StoragerControllerTest {

	private static final String TEST_FILE_ID = "123123";

	private static final byte[] TEST_BYTE_ARRAY = new byte[] {};

	@Mock
	private FileIDCalculatorClient fileIdCalculatorClientMock;
	
	private BucketShardingServiceRule bucketShardingServiceRule = new BucketShardingServiceRule();
	
	@Mock
	private MultipartFile fileMock;

	private StoragerController controller;

	@BeforeEach
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);

		when(fileMock.getBytes()).thenReturn(TEST_BYTE_ARRAY);
		
		when(fileIdCalculatorClientMock.calculate(any(byte[].class))).thenReturn(TEST_FILE_ID);

		controller = new StoragerController(fileIdCalculatorClientMock, bucketShardingServiceRule.mock);
	}

	@Test
	public void fileIdIsCalculatedWhenFileIsStored() throws IOException {
		controller.upload(fileMock);
		
		verify(fileIdCalculatorClientMock).calculate(TEST_BYTE_ARRAY);
	}
	
	@Test
	public void properStorageBucketClientIsRetrievedWhenFileIsStored() throws IOException {
		controller.upload(fileMock);
		
		verify(bucketShardingServiceRule.mock).get(TEST_FILE_ID);
	}
	
	@Test
	public void fileIsStoredInProperBucketStorageClient() throws IOException {
		controller.upload(fileMock);
		
		verify(bucketShardingServiceRule.defaultStorageBucketClient).create(TEST_FILE_ID, TEST_BYTE_ARRAY);
	}
}
