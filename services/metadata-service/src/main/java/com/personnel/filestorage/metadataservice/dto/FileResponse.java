package com.personnel.filestorage.metadataservice.dto;

public record FileResponse(Integer id,
    String fileName,
    String fileType,
    String fileUrl,
    Long fileSize
) {
}
