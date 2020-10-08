package org.roko.ns3.org.roko.ns3.catalog.api.model;

public class ParsedStorageEntry {

	private String name;
	private String path;
	private StorageEntryType storageEntryType;
	private String parentPath;
	
	public ParsedStorageEntry(String name, String path, StorageEntryType storageEntryType, String parentPath) {
		this.name = name;
		this.path = path;
		this.storageEntryType = storageEntryType;
		this.parentPath = parentPath;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public StorageEntryType getStorageEntryType() {
		return storageEntryType;
	}
	public void setStorageEntryType(StorageEntryType storageEntryType) {
		this.storageEntryType = storageEntryType;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
}
