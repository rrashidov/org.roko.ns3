package org.roko.ns3.org.roko.ns3.catalog.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.roko.ns3.org.roko.ns3.catalog.api.model.ParsedStorageEntry;
import org.roko.ns3.org.roko.ns3.catalog.api.model.StorageEntryType;

public class PathSeparatorParseServiceTest {

	private static final String ROOT_ENTRY_NAME = "ROOT";
	private static final String ROOT_ENTRY_PATH = "/";
	private static final StorageEntryType ROOT_ENTRY_ENTRY_TYPE = StorageEntryType.DIRECTORY;
	private static final String ROOT_ENTRY_PARENT_PATH = null;

	private static final String PATH_NOT_STARTING_WITH_SEPARATOR = "alabala";

	private PathParseService service;

	@BeforeEach
	public void setup() {
		service = new PathSeparatorParseService();
	}

	@Test
	public void pathStartsWithSeparator() {
		assertThrows(PathParseException.class, () -> {
			service.parse(PATH_NOT_STARTING_WITH_SEPARATOR);
		});
	}

	@Test
	public void rootPathIsParsedd() throws PathParseException {
		List<ParsedStorageEntry> parsedEntries = service.parse(ROOT_ENTRY_PATH);

		assertEquals(1, parsedEntries.size());

		assertThatFirstEntryIsRoot(parsedEntries);
	}

	@Test
	public void multiLevelPathIsProperlyParsed() throws PathParseException {
		List<ParsedStorageEntry> parsedEntries = service.parse("/alabala/tiroliro/somefile.somexception");

		assertThatFirstEntryIsRoot(parsedEntries);
		assertThatPathIsTransformedToProperEntries(parsedEntries);
		assertThatLastEntryIsFileEntry(parsedEntries);
	}

	@Test
	public void pathContainingOnlyDirectoriesIsProperlyParsed() throws PathParseException {
		List<ParsedStorageEntry> parsedEntries = service.parse("/alabala/tiroliro/");

		assertEquals(3, parsedEntries.size());
	}

	private void assertThatFirstEntryIsRoot(List<ParsedStorageEntry> parsedEntries) {
		ParsedStorageEntry rootEntry = parsedEntries.get(0);
		assertEquals(ROOT_ENTRY_NAME, rootEntry.getName());
		assertEquals(ROOT_ENTRY_PATH, rootEntry.getPath());
		assertEquals(ROOT_ENTRY_ENTRY_TYPE, rootEntry.getStorageEntryType());
		assertEquals(ROOT_ENTRY_PARENT_PATH, rootEntry.getParentPath());
	}

	private void assertThatPathIsTransformedToProperEntries(List<ParsedStorageEntry> parsedEntries) {
		ParsedStorageEntry firstDir = parsedEntries.get(1);
		assertEquals("alabala", firstDir.getName());
		assertEquals("/alabala/", firstDir.getPath());
		assertEquals(StorageEntryType.DIRECTORY, firstDir.getStorageEntryType());
		assertEquals("/", firstDir.getParentPath());

		ParsedStorageEntry secondDir = parsedEntries.get(2);
		assertEquals("tiroliro", secondDir.getName());
		assertEquals("/alabala/tiroliro/", secondDir.getPath());
		assertEquals(StorageEntryType.DIRECTORY, secondDir.getStorageEntryType());
		assertEquals("/alabala/", secondDir.getParentPath());
	}
	
	private void assertThatLastEntryIsFileEntry(List<ParsedStorageEntry> parsedEntries) {
		ParsedStorageEntry fileEntry = parsedEntries.get(3);
		assertEquals("somefile.somexception", fileEntry.getName());
		assertEquals("/alabala/tiroliro/somefile.somexception", fileEntry.getPath());
		assertEquals(StorageEntryType.FILE, fileEntry.getStorageEntryType());
		assertEquals("/alabala/tiroliro/", fileEntry.getParentPath());
	}

}
