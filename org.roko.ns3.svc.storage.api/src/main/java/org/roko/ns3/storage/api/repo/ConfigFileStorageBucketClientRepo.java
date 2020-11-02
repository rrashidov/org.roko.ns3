package org.roko.ns3.storage.api.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.roko.ns3.storage.api.repo.model.StorageBucketClientConfig;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClientFactory;
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
	public Map<String, StorageBucketClient> list() {
		Map<String, StorageBucketClient> result = new HashMap<String, StorageBucketClient>();

		Type configListType = new TypeToken<Map<String, StorageBucketClientConfig>>() {
		}.getType();

		Gson gson = new Gson();

		try {
			Map<String, StorageBucketClientConfig> storageBucketClientConfigs = (Map<String, StorageBucketClientConfig>) gson
					.fromJson(new FileReader(new File(configFilePath)), configListType);

			for (Entry<String, StorageBucketClientConfig> entry : storageBucketClientConfigs.entrySet()) {
				result.put(entry.getKey(), StorageBucketClientFactory.get(entry.getKey(), entry.getValue().getServiceUrl()));
			}
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

}
