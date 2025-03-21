package com.personnel.filestorage.metadataservice.repository;

import com.personnel.filestorage.metadataservice.entity.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileShareRepository extends JpaRepository<FileShare,Integer> {
}
