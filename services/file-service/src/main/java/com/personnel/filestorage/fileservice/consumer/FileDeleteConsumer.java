package com.personnel.filestorage.fileservice.consumer;

import com.personnel.filestorage.fileservice.dto.FileDeleteRequest;
import com.personnel.filestorage.fileservice.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileDeleteConsumer {
    private final FileUploadService fileUploadService;

    @KafkaListener(topics = "file-delete", groupId = "delete-group")
    public void fileDelete(FileDeleteRequest fileDeleteRequest) throws Exception {
        log.info(String.format("File delete request received -> %s", fileDeleteRequest));
        fileUploadService.delete(fileDeleteRequest.publicId());
    }
}
