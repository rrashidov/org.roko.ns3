package org.roko.ns3.storage.api.service.rules;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.storage.api.service.BucketShardingService;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;

public class BucketShardingServiceRule {

	@Mock
	public BucketShardingService mock;
	
	@Mock
	public StorageBucketClient defaultStorageBucketClient;

	public BucketShardingServiceRule() {
		MockitoAnnotations.initMocks(this);
		
		when(mock.get(anyString())).thenReturn(defaultStorageBucketClient);
	}
}
