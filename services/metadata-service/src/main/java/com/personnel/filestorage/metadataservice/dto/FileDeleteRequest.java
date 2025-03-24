package com.personnel.filestorage.metadataservice.dto;

import lombok.Builder;

@Builder
public record FileDeleteRequest(
        String publicId
) {
}
