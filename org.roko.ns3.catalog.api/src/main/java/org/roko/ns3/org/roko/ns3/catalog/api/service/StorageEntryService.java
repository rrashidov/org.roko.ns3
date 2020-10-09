package org.roko.ns3.org.roko.ns3.catalog.api.service;

import java.util.List;

import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryDTO;

public interface StorageEntryService {

	public void persistDTOs(List<StorageEntryDTO> storageEntries);
}
