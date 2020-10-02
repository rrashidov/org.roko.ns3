package org.roko.ns3.storage.api.controller;

import java.io.IOException;

import org.roko.ns3.fic.client.api.FileIDCalculatorClient;
import org.roko.ns3.storage.api.model.FileEntity;
import org.roko.ns3.storage.api.repo.FileEntityRepo;
import org.roko.ns3.storage.api.service.BucketShardingService;
import org.roko.ns3.storage.bucket.client.api.StorageBucketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/files")
public class StorageController {

	private FileIDCalculatorClient fileIdCalculatorClient;
	private BucketShardingService bucketSharddingService;
	private FileEntityRepo fileRepo;

	@Autowired
	public StorageController(FileIDCalculatorClient fileIdCalculatorClient, BucketShardingService bucketSharddingService, FileEntityRepo fileRepo) {
		this.fileIdCalculatorClient = fileIdCalculatorClient;
		this.bucketSharddingService = bucketSharddingService;
		this.fileRepo = fileRepo;
	}

	@GetMapping
	public String get() {
		return "Hello World!";
	}
	
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file) throws IOException {
		byte[] fileData = file.getBytes();
		String fileId = fileIdCalculatorClient.calculate(fileData);
		
		StorageBucketClient storageBucketClient = bucketSharddingService.get(fileId);
		
		storageBucketClient.create(fileId, fileData);
		
		fileRepo.saveAndFlush(new FileEntity(fileId, storageBucketClient.getId()));
		
		return fileId;
	}
}
