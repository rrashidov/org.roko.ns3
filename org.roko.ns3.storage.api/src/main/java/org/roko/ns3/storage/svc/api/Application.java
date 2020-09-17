package org.roko.ns3.storage.svc.api;

import org.roko.ns3.fic.client.api.FileIDCalculatorClient;
import org.roko.ns3.fic.client.api.FileIDCalculatorClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	@Value("${org.roko.ns3.fic.svc.url}")
	private String fileIdCalculatorServiceUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public FileIDCalculatorClient getFileIdCalculatorClient() {
		return FileIDCalculatorClientFactory.get(fileIdCalculatorServiceUrl);
	}
}
