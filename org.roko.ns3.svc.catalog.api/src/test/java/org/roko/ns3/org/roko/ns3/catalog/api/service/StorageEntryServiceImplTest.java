package org.roko.ns3.org.roko.ns3.catalog.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryDTO;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryEntity;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryType;
import org.roko.ns3.org.roko.ns3.catalog.api.repo.rules.StorageEntryRepoRule;
import org.roko.ns3.org.roko.ns3.catalog.api.service.rules.IdGeneratorServiceRule;
import org.springframework.data.domain.Example;

public class StorageEntryServiceImplTest {

	private static final String TEST_PARENT_PATH = "test_parent_path";

	private static final StorageEntryType TEST_ENTRY_TYPE = StorageEntryType.DIRECTORY;

	private static final String TEST_NAME = "test_name";

	private static final String TEST_PATH = "test_path";

	private StorageEntryDTO storageEntryDTO;

	@Mock
	private StorageEntryEntity storageEntryEntityMock;

	private List<StorageEntryDTO> storageEntries;

	private StorageEntryRepoRule storageEntryRepoRule = new StorageEntryRepoRule();

	private IdGeneratorServiceRule idGeneratorServiceRule = new IdGeneratorServiceRule();

	private StorageEntryService service;

	@Captor
	private ArgumentCaptor<Example<StorageEntryEntity>> exampleArgumentCaptor;

	@Captor
	private ArgumentCaptor<StorageEntryEntity> storageEntryEntityArgumentCaptor;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		service = new StorageEntryServiceImpl(storageEntryRepoRule.mock, idGeneratorServiceRule.mock);

		storageEntryDTO = mockStorageEntryDTO();

		storageEntries = new ArrayList<StorageEntryDTO>();
		storageEntries.add(storageEntryDTO);
	}

	@Test
	public void serviceChecksIfEntryExists() {
		service.persistDTOs(storageEntries);

		verify(storageEntryRepoRule.mock).findOne(exampleArgumentCaptor.capture());

		Example<StorageEntryEntity> capturedExample = exampleArgumentCaptor.getValue();

		StorageEntryEntity storageeEntityEntryProbe = capturedExample.getProbe();

		assertEquals(TEST_PATH, storageeEntityEntryProbe.getPath());
	}

	@Test
	public void entryIsNotPersistedIfPathFound() {
		storageEntryRepoRule.stubFindOne(storageEntryEntityMock);

		service.persistDTOs(storageEntries);

		verify(storageEntryRepoRule.mock, never()).saveAndFlush(any(StorageEntryEntity.class));
	}

	@Test
	public void entryIsPersistedIfPathNotFound() {
		service.persistDTOs(storageEntries);

		verify(storageEntryRepoRule.mock).saveAndFlush(storageEntryEntityArgumentCaptor.capture());

		StorageEntryEntity capturedStorageEntryEntity = storageEntryEntityArgumentCaptor.getValue();

		assertEquals(IdGeneratorServiceRule.DEFAULT_ID, capturedStorageEntryEntity.getId());
		assertEquals(TEST_NAME, capturedStorageEntryEntity.getName());
		assertEquals(TEST_PATH, capturedStorageEntryEntity.getPath());
		assertEquals(TEST_ENTRY_TYPE, capturedStorageEntryEntity.getEntryType());
		assertEquals(TEST_PARENT_PATH, capturedStorageEntryEntity.getParentPath());
	}

	private StorageEntryDTO mockStorageEntryDTO() {
		return new StorageEntryDTO(TEST_NAME, TEST_PATH, TEST_ENTRY_TYPE, TEST_PARENT_PATH);
	}

}
