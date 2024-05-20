package com.example.demo.Controller;

import com.example.demo.DTO.SalaryDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Model.*;
import com.example.demo.Service.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/CityFlow")
public class HRAdministratorController {

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AccountantService accountantService;

    @Autowired
    private TerminationService terminationService;

    @Autowired
    private SalaryService salaryService;

    @PostMapping (path = "/addUser")
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
                    userDTO.getRoles(),
                    true
            );
            this.userService.addUser(newUser);

            String role = userDTO.getRoles();
            if (role.equals("ROLE_DRIVER")) {
                Driver newDriver = new Driver();
                newDriver.setUser(newUser);
                this.driverService.save(newDriver);
            } else if(role.equals("ROLE_Accountant")){
                Accountant newAccountant = new Accountant();
                newAccountant.setUser(newUser);
                this.accountantService.save(newAccountant);
            }

            sendRegistrationEmail(newUser.getEmail(), newUser.getPassword(), newUser.getUsername());

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

        }
    }

    @PostMapping("/deleteUser/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer userId, @RequestBody String reason) {
        User user = userService.getById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setEmployed(false);
        userService.save(user);

        Termination termination = new Termination();
        termination.setUser(user);
        termination.setTerminationDate(LocalDate.now());
        termination.setReason(reason);
        terminationService.save(termination);

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
        }
    }

    @PostMapping(consumes = "application/json", value = "/updateUser/{userId}")
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
            if (userDTO.getDateOfBirth() != null) {
                existingUser.setDateOfBirth(userDTO.getDateOfBirth());
            }
            if (userDTO.isEmployed()) {
                existingUser.setEmployed(userDTO.isEmployed());
            }

            userService.save(existingUser);

            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/usersByRole")
    public ResponseEntity<List<User>> getUsersByRole() {
        List<User> usersByRole = new ArrayList<>();
        List<String> roles = Arrays.asList("ROLE_ROUTEADMINISTRATOR", "ROLE_HRAdministrator", "ROLE_DRIVER", "ROLE_SERVICER", "ROLE_Accountant");
        for (String role : roles) {
            List<User> usersForRole = userService.getUsersByRole(role);
            for (User user : usersForRole) {
                if (user.isEmployed()) {
                    usersByRole.add(user);
                }
            }
        }
        return new ResponseEntity<>(usersByRole, HttpStatus.OK);
    }

    @PostMapping("/assignSalary/{userId}")
    public ResponseEntity<User> assignSalary(@PathVariable Integer userId, @RequestBody SalaryDTO salaryDTO) {
        User user = userService.findById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!user.isEmployed()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Salary salary = new Salary();
        salary.setUser(user);
        salary.setBaseSalary(salaryDTO.getBaseSalary());
        salary.setOvertimeHours(salaryDTO.getOvertimeHours());
        salary.setHolidayWorkHours(salaryDTO.getHolidayWorkHours());
        salary.setNightShiftHours(salaryDTO.getNightShiftHours());
        salary.setSickLeaveHours(salaryDTO.getSickLeaveHours());
        salary.setSickLeaveType(salaryDTO.getSickLeaveType());
        salary.setOvertimePayRate(salaryDTO.getOvertimePayRate());
        salary.setHolidayPayRate(salaryDTO.getHolidayPayRate());
        salary.setNightShiftPayRate(salaryDTO.getNightShiftPayRate());
        salary.setTotalSalary(salaryDTO.getBaseSalary()); // Initially setting totalSalary to baseSalary

        salaryService.save(salary);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}