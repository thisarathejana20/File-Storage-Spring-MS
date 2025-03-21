package com.personnel.filestorage.notificationservice.dto;

public record AccountActivationConsumer(
        String token,
        String email,
        String fullName,
        String activationUrl,
        String templateType
) {
}
