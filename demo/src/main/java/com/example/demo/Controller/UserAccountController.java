package com.example.demo.Controller;

import com.example.demo.DTO.EditProfileDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserAccountController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/Account/updateProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_DRIVER','ROLE_SERVICER','ROLE_HRAdministrator', 'ROLE_Accountant')")
    public ResponseEntity<String>updateProfile(@RequestHeader("Authorization") String authorisation,@RequestBody EditProfileDTO requestBody){
        String bearerToken = authorisation.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        User user = userService.FindByUsername(username);
        if(user != null){
            user.setEmail(requestBody.getEmail());
            user.setName(requestBody.getName());
            user.setLastname(requestBody.getLastname());
            user.setDateOfBirth(requestBody.getDateOfBirth());
            user.setPhoneNumber(requestBody.getPhoneNumber());
            user.setUsername(requestBody.getUsername());
            userService.save(user);
        }
        if (!username.equals("")){
            return new ResponseEntity<>(username, HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity<>("Invalid request!",HttpStatusCode.valueOf(400));
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

