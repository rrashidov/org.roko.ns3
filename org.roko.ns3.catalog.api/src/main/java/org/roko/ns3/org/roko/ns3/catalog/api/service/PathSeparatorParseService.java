package org.roko.ns3.org.roko.ns3.catalog.api.service;

import java.util.LinkedList;
import java.util.List;

import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryDTO;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryType;

public class PathSeparatorParseService implements PathParseService {

	private static final String ROOT_ENTRY_NAME = "ROOT";
	
	private static final String SEPARATOR = "/";

	@Override
	public List<StorageEntryDTO> parse(String path) throws PathParseException {
		List<StorageEntryDTO> result = new LinkedList<StorageEntryDTO>();

		if (!path.startsWith(SEPARATOR)) {
			throw new PathParseException("Path should start with sepparator: " + path);
		}

		StringBuilder accumulatedPath = new StringBuilder();
		
		String remainingPathToParse = path;
		
		String parentStorageEntryPath = null;

		while (thereIsRemainingPathToParse(remainingPathToParse)) {
			String name = remainingPathToParse.substring(0, remainingPathToParse.indexOf(SEPARATOR));
			String normalizedName = normalizeName(name);

			accumulatedPath.append(name + SEPARATOR);

			StorageEntryDTO parsedStorageEntry = new StorageEntryDTO(normalizedName, accumulatedPath.toString(),
					StorageEntryType.DIRECTORY, parentStorageEntryPath);
			
			result.add(parsedStorageEntry);
			
			parentStorageEntryPath = parsedStorageEntry.getPath();

			remainingPathToParse = remainingPathToParse.substring(remainingPathToParse.indexOf(SEPARATOR) + 1);
		}

		if (!remainingPathToParse.isEmpty()) {
			accumulatedPath.append(remainingPathToParse);
			result.add(new StorageEntryDTO(normalizeName(remainingPathToParse), accumulatedPath.toString(),
					StorageEntryType.FILE, parentStorageEntryPath));
		}
		
		return result;
	}

	private String normalizeName(String name) {
		if (name.isEmpty()) {
			return ROOT_ENTRY_NAME;
		}

		return name;
	}

	private boolean thereIsRemainingPathToParse(String remainingPathToParse) {
		return remainingPathToParse.indexOf(SEPARATOR) != -1;
	}
}
