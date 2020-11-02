package org.roko.ns3.storage.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.roko.ns3.storage.api.repo.rules.StorageBucketClientRepoRule;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;

public class PreconfiguredBucketShardingServiceTest {

	private StorageBucketClientRepoRule storageBucketClientRepoRule = new StorageBucketClientRepoRule();

	private BucketShardingService service;

	@BeforeEach
	public void setup() {
		service = new PreconfiguredBucketShardingService(storageBucketClientRepoRule.mock);
	}

	@Test
	public void theSameClientIsReturnedWhenOnlyASingleOneIsConfigured() {
		storageBucketClientRepoRule.stubClientsCount(1);
		
		StorageBucketClient firstRetrievedClient = service.get("100");

		for (int i = 101; i < 200; i++) {
			StorageBucketClient storageBucketClient = service.get(String.valueOf(i));

			assertEquals(firstRetrievedClient, storageBucketClient);
		}
	}
	
	@Test
	public void properClientIsReturnedWhenMultipleConfigured() {
		StorageBucketClient firstRetrievedClient = service.get("1");
		assertEquals(storageBucketClientRepoRule.get(1), firstRetrievedClient);
		
		StorageBucketClient secondRetrievedClient = service.get("2");
		assertEquals(storageBucketClientRepoRule.get(2), secondRetrievedClient);
		
		StorageBucketClient thirdRetrievedClient = service.get("3");
		assertEquals(storageBucketClientRepoRule.get(3), thirdRetrievedClient);
		
		StorageBucketClient fourthRetrievedClient = service.get("4");
		assertEquals(storageBucketClientRepoRule.get(0), fourthRetrievedClient);
		
		StorageBucketClient fifthRetrievedClient = service.get("5");
		assertEquals(storageBucketClientRepoRule.get(1), fifthRetrievedClient);
		
	}
}
