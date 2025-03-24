package com.personnel.filestorage.notificationservice.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendAccountActivationEmail(String to,
                   String username,
                   String confirmationUrl,
                   String templateName,
                   String activationCode,
                   String subject) throws MessagingException;
    void sendFileSharedEmail(String to,
                             String fileName,
                             String permission,
                             String templateName,
                             String subject) throws MessagingException;
}
