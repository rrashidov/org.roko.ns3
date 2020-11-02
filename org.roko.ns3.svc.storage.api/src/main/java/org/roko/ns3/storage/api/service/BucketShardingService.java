package org.roko.ns3.storage.api.service;

import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;

public interface BucketShardingService {

	public StorageBucketClient get(String id);
}
