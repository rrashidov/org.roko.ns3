package org.roko.ns3.storage.bucket.client.api;

import java.util.List;

public interface StorageBucketClient {

	public void create(String name, byte[] data);
	
	public byte[] read(String name);
	
	public void delete(String name);
	
	public List<String> list();
}
