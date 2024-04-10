package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import com.example.demo.Service.DriverService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/CityFlow")
public class HRAdministratorController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private JavaMailSender emailSender;

    @PutMapping(consumes = "application/json", path = "/addUser")
    //@PreAuthorize("hasAuthority('ROLE_HRADMIN')")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) {
        User existingUser = this.userService.findByEmail(userDTO.getEmail());

        if(existingUser != null){
            return new ResponseEntity("User already exists", HttpStatus.FORBIDDEN);
        } else {
            User newUser = new User(
                    userDTO.getUsername(),
                    userDTO.getName(),
                    userDTO.getLastname(),
                    userDTO.getEmail(),
                    userDTO.getPassword(),
                    userDTO.getDateOfBirth(),
                    userDTO.getPhoneNumber(),
                    userDTO.getRoles()
            );
            this.userService.addUser(newUser);
            if(Objects.equals(newUser.getRoles(), "ROLE_DRIVER")) {
                Driver newDriver = new Driver(newUser);
                this.driverService.save(newDriver);
            }
        sendRegistrationEmail(newUser.getEmail(), newUser.getPassword(),newUser.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

    private void sendRegistrationEmail(String email, String password, String username) {
        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(email);
            helper.setSubject("Welcome to CityFlow Platform");

            String htmlContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body {font-family: Arial, sans-serif;}"
                    + ".container {max-width: 600px; margin: auto;}"
                    + ".header {background-color: #2F3640; color: white; padding: 20px; text-align: center;}"
                    + ".content {background-color: #353B48; color: #f5f6fa; padding: 20px;}"
                    + ".footer {background-color: #2F3640; color: white; padding: 10px; text-align: center;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='container'>"
                    + "<div class='header'>"
                    + "<h1>Welcome to CityFlow Platform</h1>"
                    + "</div>"
                    + "<div class='content'>"
                    + "<p>Dear " + username + ",</p>"
                    + "<p>We are pleased to welcome you to CityFlow, where innovation meets excellence. As a valued member of our team, you now have access to our comprehensive platform designed to streamline operations and enhance collaboration across the organization.</p>"
                    + "<p>Your login credentials are as follows:</p>"
                    + "<ul>"
                    + "<li><strong>Username:</strong> " + username + "</li>"
                    + "<li><strong>Password:</strong> " + password + "</li>"
                    + "</ul>"
                    + "<p>Please keep this information secure and do not share it with anyone. Upon your first login, you will be required to change your password for added security. You will not be able to proceed further until you change your password!</p>"
                    + "<p>If you have any questions or require assistance, please do not hesitate to reach out to our HR department at <a href='mailto:isaisanovicnnba@gmail.com'>isaisanovicnnba@gmail.com</a>.</p>"
                    + "<p>Once again, welcome to CityFlow. We look forward to working together and achieving great success.</p>"
                    + "<p>Best regards,</p>"
                    + "<p>Your CityFlow Team!</p>"
                    + "</div>"
                    + "<div class='footer'>"
                    + "<p>This is an automated message. Please do not reply.</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

   @DeleteMapping(path = "/deleteUser/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
        User user = userService.getById(userId);
           Driver driver = driverService.getByUser(user);
           if (driver != null) {
               driverService.delete(driver);
           }
            userService.deleteById(userId);
            sendDeletionEmail(user.getEmail(), user.getUsername());

            return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendDeletionEmail(String email, String username) {
        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(email);
            helper.setSubject("Notice: Your CityFlow Account Has Been Deleted");

            String htmlContent = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body {font-family: Arial, sans-serif;}"
                    + ".container {max-width: 600px; margin: auto;}"
                    + ".header {background-color: #2F3640; color: white; padding: 20px; text-align: center;}"
                    + ".content {background-color: #353B48; color: #f5f6fa; padding: 20px;}"
                    + ".footer {background-color: #2F3640; color: white; padding: 10px; text-align: center;}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<div class='container'>"
                    + "<div class='header'>"
                    + "<h1>Notice: Your CityFlow Account Has Been Deleted</h1>"
                    + "</div>"
                    + "<div class='content'>"
                    + "<p>Dear " + username + ",</p>"
                    + "<p>We regret to inform you that your CityFlow account has been terminated. As you may already be aware, your employment with our company has come to an end.</p>"
                    + "<p>Unfortunately, this means that your CityFlow account has also been removed from our system. You are welcome to create a new account as a user, but please note that your access as an employee will not be reinstated.</p>"
                    + "<p>If you have any questions or require assistance, please feel free to contact our support team at <a href='mailto:isaisanovicnnba@gmail.com'>isaisanovicnnba@gmail.com</a>.</p>"
                    + "<p>Thank you for your understanding.</p>"
                    + "<p>Your CityFlow Team.</p>"
                    + "</div>"
                    + "<div class='footer'>"
                    + "<p>This is an automated message. Please do not reply.</p>"
                    + "</div>"
                    + "</div>"
                    + "</body>"
                    + "</html>";

            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    @PutMapping(consumes = "application/json", value = "/updateUser/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        User existingUser = userService.findById(userId);

        if (existingUser != null) {
            if (userDTO.getUsername() != null && !userDTO.getUsername().isEmpty()) {
                existingUser.setUsername(userDTO.getUsername());
            }
            if (userDTO.getName() != null && !userDTO.getName().isEmpty()) {
                existingUser.setName(userDTO.getName());
            }
            if (userDTO.getLastname() != null && !userDTO.getLastname().isEmpty()) {
                existingUser.setLastname(userDTO.getLastname());
            }
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
                existingUser.setEmail(userDTO.getEmail());
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                existingUser.setPassword(userDTO.getPassword());
            }
            if (userDTO.getPhoneNumber() != null && !userDTO.getPhoneNumber().isEmpty()) {
                existingUser.setPhoneNumber(userDTO.getPhoneNumber());
            }
            if (userDTO.getRoles() != null && !userDTO.getRoles().isEmpty()) {
                existingUser.setRoles(userDTO.getRoles());
            }
            if (userDTO.getDateOfBirth() != null) {
                existingUser.setDateOfBirth(userDTO.getDateOfBirth());
            }

            userService.save(existingUser);

            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}