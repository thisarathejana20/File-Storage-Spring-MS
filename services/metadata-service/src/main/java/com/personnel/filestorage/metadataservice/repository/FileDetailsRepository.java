package com.personnel.filestorage.metadataservice.repository;

import com.personnel.filestorage.metadataservice.entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDetailsRepository extends JpaRepository<FileDetails,Integer> {
}
