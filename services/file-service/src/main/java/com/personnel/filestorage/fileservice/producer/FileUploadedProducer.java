package com.personnel.filestorage.fileservice.producer;

import com.personnel.filestorage.fileservice.dto.FileDetailsRequest;
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
public class FileUploadedProducer {
    private final KafkaTemplate<String, FileDetailsRequest> kafkaTemplate;

    public void sendMessage(FileDetailsRequest fileDetailsRequest) {
        log.info(String.format("File uploaded request sent -> %s", fileDetailsRequest));
        Message<FileDetailsRequest> message = MessageBuilder.withPayload(fileDetailsRequest)
                .setHeader(KafkaHeaders.TOPIC, "file-uploaded")
                .build();
        kafkaTemplate.send(message);
    }
}
