package com.personnel.filestorage.metadataservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personnel.filestorage.metadataservice.dto.FileResponse;
import com.personnel.filestorage.metadataservice.dto.FileShareProducer;
import com.personnel.filestorage.metadataservice.dto.FileShareRequest;
import com.personnel.filestorage.metadataservice.dto.FileUploadedConsumer;
import com.personnel.filestorage.metadataservice.entity.FileDetails;
import com.personnel.filestorage.metadataservice.entity.FileShare;
import com.personnel.filestorage.metadataservice.producer.FileSharedProducer;
import com.personnel.filestorage.metadataservice.repository.FileDetailsRepository;
import com.personnel.filestorage.metadataservice.repository.FileShareRepository;
import com.personnel.filestorage.metadataservice.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileShareRepository fileShareRepository;
    private final FileDetailsRepository fileDetailsRepository;
    private final ObjectMapper objectMapper;
    private final FileSharedProducer fileSharedProducer;

    @Override
    public void saveFile(FileUploadedConsumer fileUploadedConsumer) {
        fileDetailsRepository.save(
                objectMapper.convertValue(fileUploadedConsumer, FileDetails.class));
    }

    @Override
    public String shareFile(FileShareRequest fileShareRequest) {
        FileDetails fileDetails = fileDetailsRepository.findById(fileShareRequest.fileId())
                .orElseThrow(() -> new RuntimeException("File not found"));
        fileShareRepository.save(FileShare.builder()
                .file(fileDetails)
                .sharedEmail(fileShareRequest.email())
                .permission(fileShareRequest.permission())
                .build());
        fileSharedProducer.sendMessage(
                FileShareProducer.builder()
                        .email(fileShareRequest.email())
                        .permission(fileShareRequest.permission())
                        .fileName(fileDetails.getFileName())
                        .templateType("file_share")
                        .build()
        );
        return "File shared successfully";
    }

    @Override
    public String deleteFile(String fileId) {
        fileDetailsRepository.findById(Integer.parseInt(fileId))
                .orElseThrow(() -> new RuntimeException("File not found"));
        fileDetailsRepository.deleteById(Integer.parseInt(fileId));
        return "File deleted successfully";
    }

    @Override
    @Transactional
    public String removeFileShare(String fileId, String email) {
        fileDetailsRepository.findById(Integer.parseInt(fileId))
                .orElseThrow(() -> new RuntimeException("File not found"));
        fileShareRepository.deleteByFileIdAndSharedEmail(Integer.parseInt(fileId), email);
        return "File share removed successfully";
    }

    @Override
    @Transactional
    public String updatePermission(String fileId, String email, String permission) {
        fileDetailsRepository.findById(Integer.parseInt(fileId))
                .orElseThrow(() -> new RuntimeException("File not found"));
        fileShareRepository.updatePermission(Integer.parseInt(fileId), email, permission);
        return "File permission updated successfully";
    }

    @Override
    public String updateFileDetails(Integer id, String fileName) {
        FileDetails fileDetails = fileDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        fileDetails.setFileName(fileName);
        fileDetailsRepository.save(fileDetails);
        return "File name updated successfully";
    }

    @Override
    public List<FileResponse> getFiles(String email) {
        List<FileDetails> byOwnerEmail = fileDetailsRepository.findByOwnerEmail(email);
        if (byOwnerEmail.isEmpty()) {
            return new ArrayList<>();
        }
        return objectMapper.convertValue(byOwnerEmail,
                objectMapper.getTypeFactory().constructCollectionType(List.class, FileResponse.class));
    }

    @Override
    public List<FileResponse> getSharedFiles(String email) {
        List<FileShare> bySharedEmail = fileShareRepository.findBySharedEmail(email);
        List<FileResponse> files = new ArrayList<>();
        bySharedEmail.forEach(fileShare -> {
            FileDetails fileDetails = fileDetailsRepository.findById(fileShare.getFile().getId())
                    .orElseThrow(() -> new RuntimeException("File not found"));
            files.add(objectMapper.convertValue(fileDetails, FileResponse.class));
        });
        return files;
    }
}
