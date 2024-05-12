package com.m2bar.Banking.System.service.impl;

import com.m2bar.Banking.System.models.EmailRequest;
import com.m2bar.Banking.System.models.StringResponse;
import com.m2bar.Banking.System.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public StringResponse sendSimpleMessage(EmailRequest emailRequest) {
        try {
            Random random = new Random();
            int verificationCodeInt = random.nextInt(900000) + 100000;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(emailRequest.getEmail());
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getText() + "\n \n Here is the code: " + verificationCodeInt);
            javaMailSender.send(message);

            String verificationCode = String.valueOf(verificationCodeInt);
            StringResponse response = new StringResponse();
            response.setVerificationCode(verificationCode);
            response.setSuccessMessage("Email Successfully sent");

            return response;
        } catch (ResponseStatusException e) {
            StringResponse response = new StringResponse();
            response.setErrorMessage(e.getReason());
            return response;
        }
    }
}
