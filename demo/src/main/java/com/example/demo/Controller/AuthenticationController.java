package com.example.demo.Controller;

import com.example.demo.DTO.LoginDTO;
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

//    @PostMapping(path = "/save")
//    public ResponseEntity<User> save(@RequestBody String username){
//        User newUser = new User(username)
//    }
    @PostMapping(path = "/CityFlow/RegisterUser")
    public ResponseEntity<String> SaveUser(@RequestBody User requestBody){
        if(userService.save(requestBody) != null){
            return new ResponseEntity<>("Saved!", HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity<>("Not saved!", HttpStatusCode.valueOf(200));

        }
    }

    @GetMapping(path = "/CityFlow/Login")
    public ResponseEntity<String> Login(@RequestBody LoginDTO loginData){
        User user = userService.FindByUsername(loginData.getUsername());
        if(user != null)
        {
            if (loginData.getPassword().equals(user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                return new ResponseEntity<>(token, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Bad credentials", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getTokenUsername")
    public String getTokenUsername(@RequestBody String token){
        return jwtService.extractUsername(token);
    }
    @GetMapping(value = "/testToken")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getTokenTest(){
        return "It works !";
    }

}
