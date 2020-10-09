package org.roko.ns3.org.roko.ns3.catalog.api.repo.rules;

import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.org.roko.ns3.catalog.api.StorageEntryRepo;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryEntity;
import org.springframework.data.domain.Example;

public class StorageEntryRepoRule {

	@Mock
	public StorageEntryRepo mock;

	@SuppressWarnings("unchecked")
	public StorageEntryRepoRule() {
		MockitoAnnotations.initMocks(this);
		
		when(mock.findOne(any(Example.class))).thenReturn(Optional.empty());
	}
	
	@SuppressWarnings("unchecked")
	public void stubFindOne(StorageEntryEntity entity) {
		when(mock.findOne(any(Example.class))).thenReturn(Optional.of(entity));
	}
}
