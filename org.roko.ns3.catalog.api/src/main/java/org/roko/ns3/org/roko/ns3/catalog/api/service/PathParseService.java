package org.roko.ns3.org.roko.ns3.catalog.api.service;

import java.util.List;

import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryDTO;

public interface PathParseService {

	public List<StorageEntryDTO> parse(String path) throws PathParseException;
}
