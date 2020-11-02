package org.roko.ns3.org.roko.ns3.catalog.api;

import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageEntryRepo extends JpaRepository<StorageEntryEntity, String> {

}
