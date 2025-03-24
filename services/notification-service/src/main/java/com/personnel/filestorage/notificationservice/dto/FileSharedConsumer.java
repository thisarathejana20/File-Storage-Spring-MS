package com.personnel.filestorage.notificationservice.dto;

public record FileSharedConsumer(
        String email,
        String fileName,
        String permission,
        String templateType
) {
}
