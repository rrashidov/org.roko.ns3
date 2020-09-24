package org.roko.ns3.storage.bucket.api.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.storage.bucket.api.service.utils.FilesWrapper;

public class FileSystemBucketServiceTest {

	private static final String TEST_FILE_NAME = "test_file_name";

	@Mock
	private Path testPath;
	
	@Mock
	private Path rootLocationMock;
	
	@Mock
	private FilesWrapper filesMock;

	@Mock
	private InputStream inputStreamMock;

	private BucketService bucketService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		bucketService = new FileSystemBucketService(rootLocationMock, filesMock);
	}
	
	@Test
	public void saveWorksProperly() throws IOException {
		when(rootLocationMock.resolve(TEST_FILE_NAME)).thenReturn(testPath);
		
		bucketService.create(TEST_FILE_NAME, inputStreamMock);
		
		verify(filesMock).copy(inputStreamMock, testPath, StandardCopyOption.REPLACE_EXISTING);
	}
}
