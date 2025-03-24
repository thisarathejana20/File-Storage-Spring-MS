package com.personnel.filestorage.metadataservice.producer;

import com.personnel.filestorage.metadataservice.dto.FileDeleteRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileDeletedProducer {
    private final KafkaTemplate<String , FileDeleteRequest> kafkaTemplate;

    public void sendMessage(FileDeleteRequest fileDeleteRequest) {
        log.info(String.format("File deleted request sent -> %s", fileDeleteRequest));
        Message<FileDeleteRequest> build = MessageBuilder
                .withPayload(fileDeleteRequest)
                .setHeader(KafkaHeaders.TOPIC, "file-deleted").build();
        kafkaTemplate.send(build);
    }
}
