package com.personnel.filestorage.metadataservice.dto;

import lombok.Builder;

@Builder
public record FileUploadedConsumer(
        String fileName,
        String fileType,
        String fileUrl,
        String ownerEmail,
        Long fileSize,
        String publicId
) {
}
