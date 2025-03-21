package com.personnel.filestorage.user_service.dto;

import lombok.Builder;

@Builder
public record AccountActivationRequest(
        String token,
        String email,
        String fullName,
        String activationUrl,
        String templateType) {
}
