package com.example.demo.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    
    public void sendHtmlEmailWithImages(String to, String subject) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent = loadHtmlTemplate("static/mail.html");
        helper.setText(htmlContent, true);

        addInlineImage(helper, "image-1", "images/image-1.png");
        addInlineImage(helper, "image-2", "images/image-2.png");
        addInlineImage(helper, "image-3", "images/image-3.png");
        addInlineImage(helper, "image-4", "images/image-4.png");
        addInlineImage(helper, "image-5", "images/image-5.png");

        mailSender.send(message);
    }

    private String loadHtmlTemplate(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        byte[] htmlBytes = Files.readAllBytes(resource.getFile().toPath());
        return new String(htmlBytes, StandardCharsets.UTF_8);
    }

    private void addInlineImage(MimeMessageHelper helper, String contentId, String imagePath) throws MessagingException {
        ClassPathResource imageResource = new ClassPathResource(imagePath);
        helper.addInline(contentId, imageResource); 
    }
}


