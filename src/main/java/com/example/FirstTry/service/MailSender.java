package com.example.FirstTry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailSender {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(username);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(message);
            helper.addAttachment("test.png", new File("C:\\Games\\forUploads\\verified-user.png"));

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
