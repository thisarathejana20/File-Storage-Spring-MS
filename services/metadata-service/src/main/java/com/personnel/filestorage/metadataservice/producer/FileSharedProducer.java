package com.personnel.filestorage.metadataservice.producer;

import com.personnel.filestorage.metadataservice.dto.FileShareProducer;
import com.personnel.filestorage.metadataservice.dto.FileShareRequest;
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
public class FileSharedProducer {
    private final KafkaTemplate<String, FileShareProducer> kafkaTemplate;

    public void sendMessage(FileShareProducer fileShareProducer) {
        log.info(String.format("File shared request sent -> %s", fileShareProducer));
        Message<FileShareProducer> message = MessageBuilder.withPayload(fileShareProducer)
                .setHeader(KafkaHeaders.TOPIC, "file-shared")
                .build();
        kafkaTemplate.send(message);
    }
}
