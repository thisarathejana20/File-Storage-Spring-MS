package com.personnel.filestorage.fileservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String upload(MultipartFile multipartFile,String email) throws IOException;
    void delete(String publicId) throws IOException;
}
