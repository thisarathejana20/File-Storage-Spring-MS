package com.personnel.filestorage.metadataservice.repository;

import com.personnel.filestorage.metadataservice.dto.FileResponse;
import com.personnel.filestorage.metadataservice.entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileDetailsRepository extends JpaRepository<FileDetails,Integer> {
    @Query("UPDATE FileDetails f SET f.fileName = ?1")
    void updateFileName(String fileName);

    @Query("SELECT f FROM FileDetails f WHERE f.ownerEmail = ?1")
    List<FileDetails> findByOwnerEmail(String email);
}
