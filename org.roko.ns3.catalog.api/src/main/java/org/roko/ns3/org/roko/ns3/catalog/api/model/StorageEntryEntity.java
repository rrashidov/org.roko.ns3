package org.roko.ns3.org.roko.ns3.catalog.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StorageEntryEntity {

	@Id
	private String id;
	
	private String name;
	
	private String path;
	
	private StorageEntryType entryType;
	
	private String parentPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public StorageEntryType getEntryType() {
		return entryType;
	}

	public void setEntryType(StorageEntryType entryType) {
		this.entryType = entryType;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public static StorageEntryEntity from(String path) {
		StorageEntryEntity storageEntryEntity = new StorageEntryEntity();
		storageEntryEntity.setPath(path);
		return storageEntryEntity;
	}
	
}
