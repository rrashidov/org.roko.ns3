package org.roko.ns3.storage.api.repo;

import org.roko.ns3.storage.api.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepo extends JpaRepository<FileEntity, String> {

}
