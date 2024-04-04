package com.example.demo.Controller;

import com.example.demo.DTO.LoginDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private UserService userService;

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
    public ResponseEntity<User> Login(@RequestBody LoginDTO loginData){
        User user = userService.FindByUsername(loginData.getUsername());
        if(user != null){
            if (user.getPassword().equals(loginData.getPassword())){
                return new ResponseEntity<>(user,HttpStatusCode.valueOf(200));
            }
        }
        return new ResponseEntity<>(null,HttpStatusCode.valueOf(400));
    }
}
