package com.te.fileupload.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class FileDownloadutil {

	private Path foundfile;

	public Resource getfile(String fileName) throws IOException {
		Path path = Paths.get(
				"C:\\Users\\Rushikesh\\Documents\\workspace-spring-tool-suite-4-4.16.0.RELEASE\\Manytomany.zip_expanded\\springfrontbackend\\src\\main\\resources\\static\\files");
		Files.list(path).forEach(file -> {
			if (file.getFileName().toString().equals(fileName)) {
				foundfile = file;
				return;
			}
		});
		if (foundfile != null) {
			return new UrlResource(foundfile.toUri());
		}
		return null;
	}
}
