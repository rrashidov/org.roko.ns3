package org.roko.ns3.org.roko.ns3.catalog.api.service;

import java.util.List;
import java.util.Optional;

import org.roko.ns3.org.roko.ns3.catalog.api.StorageEntryRepo;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryDTO;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class StorageEntryServiceImpl implements StorageEntryService {

	private StorageEntryRepo storageEntryRepo;
	private IdGeneratorService idGeneratorService;

	@Autowired
	public StorageEntryServiceImpl(StorageEntryRepo storageEntryRepo, IdGeneratorService idGeneratorService) {
		this.storageEntryRepo = storageEntryRepo;
		this.idGeneratorService = idGeneratorService;
	}

	@Override
	public void persistDTOs(List<StorageEntryDTO> storageEntries) {

		for (StorageEntryDTO storageEntryDTO : storageEntries) {
			Example<StorageEntryEntity> example = Example.of(StorageEntryEntity.from(storageEntryDTO.getPath()));

			Optional<StorageEntryEntity> existingStorageEntryEntity = storageEntryRepo.findOne(example);
			
			if (!existingStorageEntryEntity.isPresent()) {
				StorageEntryEntity entity = new StorageEntryEntity();
				entity.setId(idGeneratorService.generate(storageEntryDTO.getPath()));
				entity.setName(storageEntryDTO.getName());
				entity.setPath(storageEntryDTO.getPath());
				entity.setEntryType(storageEntryDTO.getStorageEntryType());
				entity.setParentPath(storageEntryDTO.getParentPath());
				
				storageEntryRepo.saveAndFlush(entity);
			}
		}
	}
}
