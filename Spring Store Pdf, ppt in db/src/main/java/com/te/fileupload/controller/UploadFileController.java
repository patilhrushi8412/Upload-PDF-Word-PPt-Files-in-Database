package com.te.fileupload.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.fileupload.Entity.FileEntity;
import com.te.fileupload.service.StorageService;

@RestController
@RequestMapping("/files")
public class UploadFileController {

	@Autowired
	private StorageService service;

	@PostMapping("/addFile")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		String uploadFile = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK).body(uploadFile);
	}

	@GetMapping("/fromfile/{fileName}")
	public ResponseEntity<?> getFile(@PathVariable String fileName) {
		FileDownloadutil util = new FileDownloadutil();
		Resource getfile = null;
		try {
			getfile = util.getfile(fileName);
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

		if (getfile == null) {
			return new ResponseEntity<>("File Not Found", HttpStatus.NOT_FOUND);
		}
		String contentType = "application/octet-stream";
		String header = "attachment; filename=\"" + getfile.getFilename() + "\"";

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, header).body(getfile);
	}
	
	@GetMapping("/fromdb/{fileName}")
	public ResponseEntity<?> GetFilesdb(@PathVariable String fileName) {
		FileEntity file= service.getFileDB(fileName);
//		String contentType = "application/octet-s";
//		String header = "attachment; filename=\"" + file.getName() + "\"";
//		return ResponseEntity.ok().contentType(MediaType.ALL)
//				.header(HttpHeaders.CONTENT_DISPOSITION, header).body(file.getFile());
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(file.getFile());
	}
	
}
