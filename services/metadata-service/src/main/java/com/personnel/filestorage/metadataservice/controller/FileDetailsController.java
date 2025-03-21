package com.personnel.filestorage.metadataservice.controller;

import com.personnel.filestorage.metadataservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileDetailsController {
    private final FileService fileService;
}
