package com.example.demo.Controller;

import com.example.demo.DTO.EditProfileDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserAccountController {
    private static Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/Account/updateProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_DRIVER','ROLE_SERVICER','ROLE_HRAdministrator', 'ROLE_Accountant', 'ROLE_ROUTEADMINISTRATOR', 'ROLE_KYCADMINISTRATOR')")
    public ResponseEntity<?>updateProfile(@RequestHeader("Authorization") String authorisation, @RequestBody EditProfileDTO requestBody){
        String bearerToken = authorisation.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        System.out.println(bearerToken);
        User user = userService.FindByUsername(username);
        if(user != null){
            user.setEmail(requestBody.getEmail());
            user.setName(requestBody.getName());
            user.setLastname(requestBody.getLastname());
            user.setDateOfBirth(requestBody.getDateOfBirth());
            user.setPhoneNumber(requestBody.getPhoneNumber());
            user.setUsername(requestBody.getUsername());
            userService.save(user);
            return ResponseEntity.ok().body(Map.of("message", "Updated!"));
        } else{
            return ResponseEntity.badRequest().body(Map.of("message", "Invalid request!"));
        }
    }

    @GetMapping(path = "/Account/getUser")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_DRIVER','ROLE_SERVICER','ROLE_HRAdministrator', 'ROLE_Accountant')")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorisation){
        String bearerToken = authorisation.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        User user = userService.FindByUsername(username);
        if(user != null){
            return new ResponseEntity<User>(user,HttpStatusCode.valueOf(200));

        }else{
            return new ResponseEntity<User>((User) null, HttpStatusCode.valueOf(400));
        }
    }

}

