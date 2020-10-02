package org.roko.ns3.storage.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileEntity {

	@Id
	private String id;
	
	@Column
	private String bucketId;
	
	public FileEntity(String id, String bucketId) {
		this.id = id;
		this.bucketId = bucketId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBucketId() {
		return bucketId;
	}

	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}
}
