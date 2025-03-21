package com.personnel.filestorage.metadataservice.dto;

public record FileUploadedConsumer(
        String fileName,
        String fileType,
        String fileUrl,
        String ownerEmail,
        Long fileSize
) {
}
