package com.example.demo.Controller;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import com.example.demo.Service.DriverService;
import com.example.demo.Service.EmailService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

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
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Welcome to CityFlow Platform");

        String message = "Dear " + username + ",\n\n"
                + "We are pleased to welcome you to CityFlow, where innovation meets excellence. As a valued member of our team, you now have access to our comprehensive platform designed to streamline operations and enhance collaboration across the organization.\n\n"
                + "Your login credentials are as follows:\n\n"
                + "Username: " + username +"\n"
                + "Password: " + password + "\n\n"
                + "Please keep this information secure and do not share it with anyone. Upon your first login, you will be required to change your password for added security. You will not be able to proceed further until you change your password!\n\n"
                + "If you have any questions or require assistance, please do not hesitate to reach out to our HR department at \n" +
                "isaisanovicnnba@gmail.com.\n\n"
                + "Once again, welcome to CityFlow. We look forward to working together and achieving great success.\n\n"
                + "Best regards,\n"
                + "Your CityFlow Team!";

        mailMessage.setText(message);

        emailService.sendEmail(mailMessage);
    }

   @DeleteMapping(path = "/deleteUser/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId) {
        User user = userService.getById(userId);
            // Provera da li je korisnik vozač i izbrišite ga iz tabele vozača ako jeste
            if(user.getRoles().equals("ROLE_DRIVER")) {
                driverService.deleteByUserId(userId);
            }

            userService.deleteById(userId);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendDeletionEmail(String email, String username) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Notice: Your CityFlow Account Has Been Deleted");

        String message = "Dear " + username + ",\n\n"
                + "We regret to inform you that your CityFlow account has been deleted. We apologize for any inconvenience this may cause.\n\n"
                + "If you believe this action was taken in error or if you have any questions, please contact our support team immediately at \n" +
                "isaisanovicnnba@gmail.com.\n\n"
                + "Thank you for your understanding.\n\n"
                + "Best regards,\n"
                + "Your CityFlow Team!";

        mailMessage.setText(message);

        emailService.sendEmail(mailMessage);
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