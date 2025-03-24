package com.personnel.filestorage.metadataservice.dto;

import lombok.Builder;

@Builder
public record FileShareProducer(
        String email,
        String fileName,
        String permission,
        String templateType
) {
}
