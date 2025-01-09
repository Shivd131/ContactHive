package com.contact_hive.contact_hive.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.contact_hive.contact_hive.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.properties.domain_name}")
    private String domainName;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setFrom(domainName);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) {
        // TODO Auto-generated method stub
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlBody) {
        // TODO Auto-generated method stub
    }

}
