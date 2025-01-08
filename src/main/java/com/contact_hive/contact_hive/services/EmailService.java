package com.contact_hive.contact_hive.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);

    void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment);

    void sendEmailWithHtml(String to, String subject, String htmlBody); 
}
