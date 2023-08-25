package com.bitspondon.quiz.application.service;

public interface IEmailService {

    /**
     * Send a simple text email.
     *
     * @param to      Recipient email address
     * @param subject Email subject
     * @param body    Email body
     */
    void sendTextEmail(String to, String subject, String body);

    /**
     * Send an HTML email.
     *
     * @param to      Recipient email address
     * @param subject Email subject
     * @param htmlBody HTML email body
     */
    void sendHtmlEmail(String to, String subject, String htmlBody);

    /**
     * Send an email with attachments.
     *
     * @param to       Recipient email address
     * @param subject  Email subject
     * @param body     Email body
     * @param filePath Path to the file to be attached
     */
    void sendEmailWithAttachment(String to, String subject, String body, String filePath);

    /**
     * Send an email to multiple recipients.
     *
     * @param to      Array of recipient email addresses
     * @param subject Email subject
     * @param body    Email body
     */
    void sendEmailToMultipleRecipients(String[] to, String subject, String body);

    // Add more methods as needed...
}
