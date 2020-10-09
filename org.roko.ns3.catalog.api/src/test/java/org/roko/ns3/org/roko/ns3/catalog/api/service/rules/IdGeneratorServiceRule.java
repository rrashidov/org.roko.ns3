package org.roko.ns3.org.roko.ns3.catalog.api.service.rules;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.org.roko.ns3.catalog.api.service.IdGeneratorService;

public class IdGeneratorServiceRule {
	
	public static final String DEFAULT_ID = "123123123123";

	@Mock
	public IdGeneratorService mock;

	public IdGeneratorServiceRule() {
		MockitoAnnotations.initMocks(this);
		
		when(mock.generate(anyString())).thenReturn(DEFAULT_ID);
	}
}
