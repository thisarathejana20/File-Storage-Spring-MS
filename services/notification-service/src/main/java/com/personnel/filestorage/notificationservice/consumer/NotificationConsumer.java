package com.personnel.filestorage.notificationservice.consumer;

import com.personnel.filestorage.notificationservice.dto.AccountActivationConsumer;
import com.personnel.filestorage.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "account-activation", groupId = "activation-group")
    public void accountActivation(AccountActivationConsumer accountActivationConsumer) throws Exception {
        log.info(String.format("Account activation request received -> %s", accountActivationConsumer));
        emailService.sendEmail(accountActivationConsumer.email(),
                accountActivationConsumer.fullName(),
                accountActivationConsumer.activationUrl(),
                accountActivationConsumer.templateType(),
                accountActivationConsumer.token(),
                "Account Activation");
    }
}
