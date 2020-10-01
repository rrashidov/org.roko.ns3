package org.roko.ns3.storage.api.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.roko.ns3.storage.api.repo.model.StorageBucketClientConfig;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Component
public class ConfigFileStorageBucketClientRepo implements StorageBucketClientRepo {

	@Value("${storage.bucket.client.config.file}")
	private String configFilePath;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StorageBucketClient> list() {
		List<StorageBucketClient> result = new ArrayList<StorageBucketClient>();
		
		Type configListType = new TypeToken<ArrayList<StorageBucketClientConfig>>(){}.getType();
		
		Gson gson = new Gson();
		
		try {
			return (List<StorageBucketClient>)gson.fromJson(new FileReader(new File(configFilePath)), configListType);
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
