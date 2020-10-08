package org.roko.ns3.org.roko.ns3.catalog.api.service;

import java.util.List;

import org.roko.ns3.org.roko.ns3.catalog.api.model.ParsedStorageEntry;

public interface PathParseService {

	public List<ParsedStorageEntry> parse(String path) throws PathParseException;
}
