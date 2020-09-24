package org.roko.ns3.storage.bucket.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface BucketService {

	public void create(String name, InputStream is) throws IOException;
	
	public byte[] read(String name);
	
	public void delete(String name);
	
	public List<String> list();
}
