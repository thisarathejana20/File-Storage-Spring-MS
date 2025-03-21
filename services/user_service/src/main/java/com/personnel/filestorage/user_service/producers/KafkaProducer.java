package com.personnel.filestorage.user_service.producers;

import com.personnel.filestorage.user_service.dto.AccountActivationRequest;
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
public class KafkaProducer {
    private final KafkaTemplate<String, AccountActivationRequest> kafkaTemplate;

    public void sendMessage(AccountActivationRequest accountActivationRequest) {
        log.info(String.format("Account activation request sent -> %s", accountActivationRequest));
        Message<AccountActivationRequest> message = MessageBuilder.withPayload(accountActivationRequest)
                .setHeader(KafkaHeaders.TOPIC, "account-activation")
                .build();
        kafkaTemplate.send(message);
    }
}
