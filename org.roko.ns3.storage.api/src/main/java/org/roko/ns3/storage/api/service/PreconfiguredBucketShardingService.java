package org.roko.ns3.storage.api.service;

import java.math.BigInteger;

import org.roko.ns3.storage.api.repo.StorageBucketClientRepo;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreconfiguredBucketShardingService implements BucketShardingService {
	
	private StorageBucketClientRepo storageBucketClientRepo;

	@Autowired
	public PreconfiguredBucketShardingService(StorageBucketClientRepo storageBucketClientRepo) {
		this.storageBucketClientRepo = storageBucketClientRepo;
	}

	@Override
	public StorageBucketClient get(String id) {
		BigInteger fileId = new BigInteger(id, 16);
		
		int storageBucketClientsCount = storageBucketClientRepo.list().size();
		
		BigInteger bucketId = fileId.remainder(new BigInteger(String.valueOf(storageBucketClientsCount), 10));
		
		return storageBucketClientRepo.list().get(String.valueOf(bucketId.intValue()));
	}

}
