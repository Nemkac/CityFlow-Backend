package com.example.demo.Service;

import com.example.demo.Model.Ticket;
import com.example.demo.Repository.TicketRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EmailService emailService;


    public void buyTicket(Integer busId, Integer routeId, String email) throws MessagingException, jakarta.mail.MessagingException {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDateTime expirationDateTime = dateTimeNow.plusHours(1);

        // Create and save the ticket
        Ticket ticket = new Ticket(busId, routeId, dateTimeNow, expirationDateTime);
        ticketRepository.save(ticket);

        // Generate the QR code for the ticket
        byte[] qrCodeBase64 = this.generateTicketQR(busId, routeId);

        // Send an email with the ticket details and QR code
        String busInfo = "Bus ID: " + busId;
        String routeInfo = "Route ID: " + routeId + "\nDate/Time: " + dateTimeNow;
        emailService.sendTicketEmail(email, "Your Ticket", qrCodeBase64, busInfo, routeInfo);
    }
    public byte[] generateTicketQR(Integer busId, Integer routeId) {
        try {
            String qrData = "busId=" + busId + "&routeId=" + routeId;
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(
                    qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, 200, 200)
            );

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray(); // Return the byte array of the QR code image
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
}
