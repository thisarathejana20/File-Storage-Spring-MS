package com.personnel.filestorage.metadataservice.consumer;

import com.personnel.filestorage.metadataservice.dto.FileUploadedConsumer;
import com.personnel.filestorage.metadataservice.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final FileService fileService;

    @KafkaListener(topics = "file-uploaded", groupId = "upload-group")
    public void fileUploaded(FileUploadedConsumer fileUploadedConsumer) throws Exception {
        log.info(String.format("File uploaded request received -> %s", fileUploadedConsumer));
        fileService.saveFile(fileUploadedConsumer);
    }
}
