package com.personnel.filestorage.metadataservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class FileDeletedTopicConfig {
    @Bean
    public NewTopic fileDeletedTopic() {
        return TopicBuilder.name("file-deleted").build();
    }
}
