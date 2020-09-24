package org.roko.ns3.storage.bucket.api.controller;

import java.io.IOException;

import org.roko.ns3.storage.bucket.api.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/files/")
public class BucketController {

	private final BucketService bucketService;

	@Autowired
	public BucketController(BucketService bucketService) {
		this.bucketService = bucketService;
	}

	@PostMapping("/{fileName}")
	public void upload(@PathVariable("fileName") String fileName, @RequestParam("file") MultipartFile file)
			throws IOException {
		bucketService.create(fileName, file.getInputStream());
	}

}
