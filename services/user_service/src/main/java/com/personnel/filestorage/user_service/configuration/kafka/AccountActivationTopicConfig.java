package com.personnel.filestorage.user_service.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AccountActivationTopicConfig {

    @Bean
    public NewTopic accountActivationTopic() {
        return TopicBuilder.name("account-activation").build();
    }
}
