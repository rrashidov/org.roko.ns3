package org.roko.ns3.storage.bucket.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.roko.ns3.storage.bucket.api.service.utils.FilesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileSystemBucketService implements BucketService {

	private final Path rootLocation;
	private final FilesWrapper files;

	@Autowired
	public FileSystemBucketService(Path rootLocation, FilesWrapper files) {
		this.rootLocation = rootLocation;
		this.files = files;
	}

	@Override
	public void create(String name, InputStream is) throws IOException {
		files.copy(is, rootLocation.resolve(name), StandardCopyOption.REPLACE_EXISTING);
	}

	@Override
	public byte[] read(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
