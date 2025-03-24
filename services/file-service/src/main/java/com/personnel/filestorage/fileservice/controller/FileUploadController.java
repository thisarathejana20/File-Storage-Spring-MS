package com.personnel.filestorage.fileservice.controller;

import com.personnel.filestorage.fileservice.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile,
                                         @RequestHeader("userEmail") String email) {
        try {
            return ResponseEntity.ok(fileUploadService.upload(multipartFile,email));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("File Upload Failed");
        }
    }
}
