package com.personnel.filestorage.fileservice.services.impl;

import com.cloudinary.Cloudinary;
import com.personnel.filestorage.fileservice.configuration.CloudinaryConfig;
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

    public String upload(MultipartFile multipartFile) throws IOException {
        Map upload = cloudinary.uploader().upload(multipartFile.getBytes(),
                Map.of("resource_type", "auto"));
        return upload.get("secure_url").toString();
    }
}
