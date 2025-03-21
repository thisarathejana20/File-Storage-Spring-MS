package com.personnel.filestorage.metadataservice.dto;

import lombok.Builder;

@Builder
public record FileShareRequest(
    String email,
    Integer fileId,
    String permission
) {
}
