package com.example.demo.Controller;

import com.example.demo.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/buy")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> buyTicket(@RequestHeader("Authorization") String authorization, @RequestParam Integer busId, @RequestParam Integer routeId, @RequestParam String email) {
        try {
            ticketService.buyTicket(busId, routeId, email);
            return new ResponseEntity<>("Ticket purchased successfully. Check your email for the ticket.", HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>("Failed to send ticket email.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to buy ticket.", HttpStatus.FORBIDDEN);
        }
    }
}
