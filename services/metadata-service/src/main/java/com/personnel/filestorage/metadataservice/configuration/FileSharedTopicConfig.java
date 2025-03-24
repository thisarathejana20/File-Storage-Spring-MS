package com.personnel.filestorage.metadataservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class FileSharedTopicConfig {
    @Bean
    public NewTopic fileSharedTopic() {
        return TopicBuilder.name("file-shared").build();
    }
}
