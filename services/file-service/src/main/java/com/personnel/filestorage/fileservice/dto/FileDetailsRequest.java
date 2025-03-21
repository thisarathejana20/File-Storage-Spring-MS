package com.personnel.filestorage.fileservice.dto;

import lombok.Builder;

@Builder
public record FileDetailsRequest(
        String fileName,
        String fileType,
        String fileUrl,
        String ownerEmail,
        Long fileSize
) {
}
