package org.roko.ns3.org.roko.ns3.fic.svc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.fix.svc.rules.ByteArrayRule;
import org.roko.ns3.fix.svc.rules.MessageDigestRule;

public class FileIdCalculatorServiceImplTest {

	private static final byte[] C_255_IN_BYTE_ARRAY=new byte[]{(byte) 255};
	
	private static final String C_255_HASHED = "000000000000000000000000000000ff";

	private MessageDigestRule messageDigestRule = new MessageDigestRule();

	private ByteArrayRule byteArrayRule = new ByteArrayRule();

	private FileIdCalculatorService fileIdCalculatorService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		fileIdCalculatorService = new FileIdCalculatorServiceImpl(messageDigestRule.mock);
	}

	@Test
	public void messageDigestIsUsedToCalculateHash() {
		fileIdCalculatorService.calculate(byteArrayRule.mock);

		verify(messageDigestRule.mock).digest(byteArrayRule.mock);
	}

	@Test
	public void digestedMessageIsProperlyFormatted() {
		String calculatedHash = fileIdCalculatorService.calculate(C_255_IN_BYTE_ARRAY);
		
		assertEquals(C_255_HASHED, calculatedHash);
	}
}
