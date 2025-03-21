package com.personnel.filestorage.metadataservice.service;

import com.personnel.filestorage.metadataservice.dto.FileResponse;
import com.personnel.filestorage.metadataservice.dto.FileShareRequest;
import com.personnel.filestorage.metadataservice.dto.FileUploadedConsumer;

import java.util.List;

public interface FileService {
    void saveFile(FileUploadedConsumer fileUploadedConsumer);

    String shareFile(FileShareRequest fileShareRequest);

    String deleteFile(String fileId);
    String removeFileShare(String fileId,String email);
    String updatePermission(String fileId, String email, String permission);
    String updateFileDetails(Integer id, String fileName);
    List<FileResponse> getFiles(String email);
    List<FileResponse> getSharedFiles(String email);
}
