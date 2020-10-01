package org.roko.ns3.storage.api;

import java.io.IOException;

import org.roko.ns3.fic.client.api.FileIDCalculatorClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
public class StoragerController {

	private FileIDCalculatorClient fileIdCalculatorClient;

	@Autowired
	public StoragerController(FileIDCalculatorClient fileIdCalculatorClient) {
		this.fileIdCalculatorClient = fileIdCalculatorClient;
	}

	@GetMapping
	public String get() {
		return "Hello World!";
	}
	
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file) throws IOException {
		return fileIdCalculatorClient.calculate(file.getBytes());
	}
}
