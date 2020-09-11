package org.roko.ns3.org.roko.ns3.fic.svc;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.org.roko.ns3.fic.svc.controller.FileIdCalculatorController;
import org.roko.ns3.org.roko.ns3.fic.svc.service.FileIdCalculatorService;
import org.springframework.web.multipart.MultipartFile;

public class FileIdCalculatorControllerTest {

	private static final byte[] TEST_BYTE_ARRAY = new byte[] {};

	@Mock
	private MultipartFile file;

	@Mock
	private FileIdCalculatorService service;
	
	private FileIdCalculatorController controller;
	
	@BeforeEach
	public void setup() throws IOException {
		MockitoAnnotations.initMocks(this);
		
		when(file.getBytes()).thenReturn(TEST_BYTE_ARRAY);
		
		controller = new FileIdCalculatorController(service);
	}
	
	@Test
	public void controllerDelegatesToService() throws IOException {
		controller.calculate(file);
		
		verify(service).calculate(TEST_BYTE_ARRAY);
	}
}
