package com.example.demo.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.services.EmailService;

import jakarta.mail.MessagingException;



@Component
public class EmailScheduler {

	@Autowired
	private UserService userService;
	
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 20 11 * * ?")  
    public void sendDailyEmails() throws IOException {
    	List<String> userEmails = userService.getAllUserEmails();

        for (String email : userEmails) {
            try {
                emailService.sendHtmlEmailWithImages(email, "News Reminder!");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}

