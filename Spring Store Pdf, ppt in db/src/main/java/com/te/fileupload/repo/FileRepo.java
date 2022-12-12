package com.te.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.fileupload.Entity.FileEntity;

public interface FileRepo extends JpaRepository<FileEntity, Integer> {

	FileEntity findByName(String fileName);

}
