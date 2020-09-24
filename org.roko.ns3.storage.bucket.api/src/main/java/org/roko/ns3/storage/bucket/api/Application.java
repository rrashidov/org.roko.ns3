package org.roko.ns3.storage.bucket.api;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.roko.ns3.storage.bucket.api.service.utils.FilesWrapper;
import org.roko.ns3.storage.bucket.api.service.utils.FilesWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	
	@Value("${root.location.path}")
	private String rootLocationPath;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FilesWrapper getFilesWrapper() {
		return new FilesWrapperImpl();
	}
	
	@Bean
	public Path getRootPath() {
		return Paths.get(rootLocationPath);
	}
}
