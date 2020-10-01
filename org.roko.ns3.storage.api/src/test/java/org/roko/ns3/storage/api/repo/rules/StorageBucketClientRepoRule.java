package org.roko.ns3.storage.api.repo.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.storage.api.repo.StorageBucketClientRepo;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;

public class StorageBucketClientRepoRule {

	public static final int DEFAULT_STORAGE_BUCKET_CLIENT_COUNT = 4;
	
	@Mock
	public StorageBucketClientRepo mock;
	
	private List<StorageBucketClient> storageBucketClients;

	public StorageBucketClientRepoRule() {
		MockitoAnnotations.initMocks(this);
		
		stubClientsCount(DEFAULT_STORAGE_BUCKET_CLIENT_COUNT);
		when(mock.list()).thenReturn(storageBucketClients);
	}
	
	public void stubClientsCount(int count) {
		storageBucketClients = new ArrayList<StorageBucketClient>();
		for (int i = 0; i < count; i++) {
			storageBucketClients.add(mock(StorageBucketClient.class));
		}
		when(mock.list()).thenReturn(storageBucketClients);
	}
	
	public StorageBucketClient get(int index) {
		return storageBucketClients.get(index);
	}
}
