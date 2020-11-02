package org.roko.ns3.storage.api.repo;

import java.util.Map;

import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageBucketClientRepo {

	public Map<String, StorageBucketClient> list();
	
}
