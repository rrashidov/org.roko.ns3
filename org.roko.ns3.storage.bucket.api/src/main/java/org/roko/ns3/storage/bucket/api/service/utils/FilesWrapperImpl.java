package org.roko.ns3.storage.bucket.api.service.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesWrapperImpl implements FilesWrapper {

	@Override
	public long copy(InputStream in, Path target, CopyOption... options) throws IOException {
		return Files.copy(in, target, options);
	}

}
