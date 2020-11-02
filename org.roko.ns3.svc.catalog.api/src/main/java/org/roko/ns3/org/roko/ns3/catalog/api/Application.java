package org.roko.ns3.org.roko.ns3.catalog.api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA-1");
	}
}
