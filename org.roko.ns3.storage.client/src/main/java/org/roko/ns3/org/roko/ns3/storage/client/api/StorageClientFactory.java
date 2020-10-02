package org.roko.ns3.org.roko.ns3.storage.client.api;

import org.roko.ns3.org.roko.ns3.storage.client.impl.StorageClientImpl;

public class StorageClientFactory {

	public static StorageClient get(String url) {
		return new StorageClientImpl(url);
	}
}
