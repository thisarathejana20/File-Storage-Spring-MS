package com.personnel.filestorage.notificationservice.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(String to,
                   String username,
                   String confirmationUrl,
                   String templateName,
                   String activationCode,
                   String subject) throws MessagingException;
}
