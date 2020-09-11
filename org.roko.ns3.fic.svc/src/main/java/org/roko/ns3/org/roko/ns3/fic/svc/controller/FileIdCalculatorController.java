package org.roko.ns3.org.roko.ns3.fic.svc.controller;

import java.io.IOException;

import org.roko.ns3.org.roko.ns3.fic.svc.service.FileIdCalculatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/")
public class FileIdCalculatorController {

	private FileIdCalculatorService fileIdCalculatorService;

	public FileIdCalculatorController(FileIdCalculatorService fileIdCalculatorService) {
		this.fileIdCalculatorService = fileIdCalculatorService;
	}

	@PostMapping("calculate")
	public String calculate(@RequestParam("file") MultipartFile file) throws IOException {
		return fileIdCalculatorService.calculate(file.getBytes());
	}
}
