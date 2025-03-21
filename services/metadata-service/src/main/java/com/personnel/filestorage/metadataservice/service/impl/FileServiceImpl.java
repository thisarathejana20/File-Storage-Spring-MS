package com.personnel.filestorage.metadataservice.service.impl;

import com.personnel.filestorage.metadataservice.repository.FileDetailsRepository;
import com.personnel.filestorage.metadataservice.repository.FileShareRepository;
import com.personnel.filestorage.metadataservice.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileShareRepository fileShareRepository;
    private final FileDetailsRepository fileDetailsRepository;
}
