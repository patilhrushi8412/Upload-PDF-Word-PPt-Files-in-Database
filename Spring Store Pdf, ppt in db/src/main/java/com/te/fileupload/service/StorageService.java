package com.te.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.fileupload.Entity.FileEntity;
import com.te.fileupload.repo.FileRepo;

@Service
public class StorageService {

	@Autowired
	FileRepo repo;

	public String uploadImage(MultipartFile file) throws IOException {

		String path = "C:\\Users\\Rushikesh\\Documents\\workspace-spring-tool-suite-4-4.16.0.RELEASE\\Manytomany.zip_expanded\\springfrontbackend\\src\\main\\resources\\static\\files";
		// String path = new
		// ClassPathResource("static/files/").getFile().getAbsolutePath();
		long copy = Files.copy(file.getInputStream(), Paths.get(path + File.separator + file.getOriginalFilename()),
				StandardCopyOption.REPLACE_EXISTING);

		repo.save(FileEntity.builder().name(file.getOriginalFilename()).type(file.getContentType())
				.file(file.getBytes()).build());

		if (copy != 0) {
			return "file uploaded successfully :" + file.getOriginalFilename();
		}
		return null;
	}

	public FileEntity getFileDB(String fileName) {
		try {
			FileEntity files = repo.findByName(fileName);
			return files;
		} catch (Exception e) {
			throw e;
		}
	}

}
