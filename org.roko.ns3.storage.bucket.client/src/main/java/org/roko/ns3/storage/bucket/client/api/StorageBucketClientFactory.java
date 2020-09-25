package org.roko.ns3.storage.bucket.client.api;

import org.roko.ns3.storage.bucket.client.impl.FileSystemStorageBucketClient;

public class StorageBucketClientFactory {

	public static StorageBucketClient get(String serviceURL) {
		return new FileSystemStorageBucketClient(serviceURL);
	}
}
