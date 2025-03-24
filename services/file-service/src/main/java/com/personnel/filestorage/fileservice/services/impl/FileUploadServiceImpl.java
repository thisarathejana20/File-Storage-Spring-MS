package com.personnel.filestorage.fileservice.services.impl;

import com.cloudinary.Cloudinary;
import com.personnel.filestorage.fileservice.dto.FileDetailsRequest;
import com.personnel.filestorage.fileservice.producer.FileUploadedProducer;
import com.personnel.filestorage.fileservice.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {
    private final Cloudinary cloudinary;
    private final FileUploadedProducer fileUploadedProducer;

    public String upload(MultipartFile multipartFile,String email) throws IOException {
        Map upload = cloudinary.uploader().upload(multipartFile.getBytes(),
                Map.of("resource_type", "auto"));
        fileUploadedProducer.sendMessage(FileDetailsRequest
                .builder()
                .fileUrl(upload.get("secure_url").toString())
                .fileName(multipartFile.getOriginalFilename())
                .fileSize(multipartFile.getSize())
                .fileType(multipartFile.getContentType())
                .ownerEmail(email)
                .build());
        return upload.get("secure_url").toString();
    }

    public void delete(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, Map.of("invalidate", true));
    }
}
