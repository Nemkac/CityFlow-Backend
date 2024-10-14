package com.example.demo.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.util.UUID;

@Service("emailService")
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }
    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }

    public void sendTicketEmail(String to, String subject, byte[] qrCodeImage, String busInfo, String routeInfo) throws MessagingException, jakarta.mail.MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);

        // Create the HTML content for the email
        String cid = UUID.randomUUID().toString(); // Generate a unique content ID for the inline image
        String emailContent = "<p><strong>Bus Information:</strong></p>" +
                "<p>" + busInfo + "</p>" +
                "<p><strong>Route Information:</strong></p>" +
                "<p>" + routeInfo + "</p>" +
                "<p><strong>QR Code:</strong></p>" +
                "<img src='cid:" + cid + "' alt='QR Code'/>";

        helper.setText(emailContent, true);

        // Attach the QR code image as an inline attachment
        ByteArrayInputStream qrStream = new ByteArrayInputStream(qrCodeImage);
        helper.addInline(cid, new ByteArrayResource(qrCodeImage), "image/png");

        javaMailSender.send(message);
    }

}
