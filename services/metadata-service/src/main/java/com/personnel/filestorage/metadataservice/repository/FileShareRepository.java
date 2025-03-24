package com.personnel.filestorage.metadataservice.repository;

import com.personnel.filestorage.metadataservice.entity.FileShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileShareRepository extends JpaRepository<FileShare,Integer> {
    @Modifying
    @Query("DELETE FROM FileShare f WHERE f.file.id = ?1 AND f.sharedEmail = ?2")
    void deleteByFileIdAndSharedEmail(int id, String email);

    @Modifying
    @Query("UPDATE FileShare f SET f.permission = ?3 WHERE f.file.id = ?1 AND f.sharedEmail = ?2")
    void updatePermission(int id, String email, String permission);

    List<FileShare> findBySharedEmail(String email);
}
