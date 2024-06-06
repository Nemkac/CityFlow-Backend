package com.example.demo.Controller;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.DTO.RegisterDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping(path = "/CityFlow/RegisterUser")
    public ResponseEntity<String> SaveUser(@RequestBody RegisterDTO requestBody){
        User newUser = new User(
                requestBody.getUsername(),
                requestBody.getName(),
                requestBody.getLastname(),
                requestBody.getEmail(),
                requestBody.getPassword(),
                requestBody.getDateOfBirth(),
                requestBody.getPhone(),
                "ROLE_USER",
                false
                );
        if(userService.save(newUser) != null){
            return new ResponseEntity<>("Saved!", HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity<>("Not saved!", HttpStatusCode.valueOf(200));

        }
    }

    @PostMapping(path = "/CityFlow/Login")
    public ResponseEntity<LoginDTO> Login(@RequestBody LoginDTO loginData){
        User user = userService.FindByUsername(loginData.getUsername());
        if(user != null)
        {
            if (loginData.getPassword().equals(user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                return new ResponseEntity<>(new LoginDTO(token, ""), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new LoginDTO("Bad credentials", "0"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new LoginDTO("User not found", "-1"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getTokenUsername")
    public String getTokenUsername(@RequestBody String token){
        return jwtService.extractUsername(token);
    }

    @GetMapping(value = "/testToken")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getTokenTest(){
        return "It works!";
    }

    @GetMapping(value = "/CityFlow/getUserByToken")
    public ResponseEntity<User> getUserByToken(@RequestParam(required = true)String token) {
        try {
            String username = jwtService.extractUsername(token);
            User user = userService.FindByUsername(username);

            if (user != null) {
                return new ResponseEntity<User>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
