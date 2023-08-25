package com.bitspondon.quiz.infrastructure.service;

import com.bitspondon.quiz.application.service.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;
    @Override
    public void sendTextEmail(String to, String subject, String body) {
        sendEmail(to, subject, body, false, null);
    }

    @Override
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        sendEmail(to, subject, htmlBody, true, null);
    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, String filePath) {
        sendEmail(to, subject, body, false, filePath);
    }

    @Override
    public void sendEmailToMultipleRecipients(String[] to, String subject, String body) {
        String recipients = String.join(",", to);
        sendEmail(recipients, subject, body, false, null);
    }

    private void sendEmail(String to, String subject, String body, boolean isHtml, String attachmentPath) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, isHtml);

            if (attachmentPath != null) {
                File attachment = new File(attachmentPath);
                helper.addAttachment(attachment.getName(), attachment);
            }

            mailSender.send(message);
        } catch (MessagingException e) {
            // Handle the exception or log the error
            e.printStackTrace();
        }
    }

    // You can add more methods here for additional email functionality

}
