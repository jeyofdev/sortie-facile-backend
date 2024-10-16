package com.poec.sortie_facile_backend.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String fromEmail;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendPasswordResetEmail(String toEmail, String resetToken) {

        String resetUrl = "http://localhost:3000/auth/reset-password?resetToken=" + resetToken;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        message.setSubject("Reset your password");
        message.setText("To reset your password, please click on the following link : " + resetUrl);

        emailSender.send(message);
    }

    public void sendUpdatePasswordEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setFrom(fromEmail);
        message.setSubject("Password Updated");
        message.setText("Good news! Your password has been successfully changed. You can now use your new password to log in to your account. If you didn’t make this change or if you have any issues, feel free to contact us as soon as possible. We’re here to help you keep your account safe.");

        emailSender.send(message);
    }

    public void sendValidationEmail(String email, String verificationToken) {
        String verificationUrl = "http://localhost:3000/auth/verification-account?verificationToken=" + verificationToken;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(fromEmail);
        message.setSubject("Verification of your account");
        message.setText("Please click the following link to verify your account: " + verificationUrl);

        emailSender.send(message);
    }
}
