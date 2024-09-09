package com.example.demo.Controller;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(consumes="application/json",value="/CityFlow/updateProfile/{userId}")
    public ResponseEntity<User> updateProfile(@RequestBody User userInfo, @PathVariable int userId){
        User user = this.userService.getUserById(userId);
        user.setName(userInfo.getName());
        user.setUsername(userInfo.getUsername());
        user.setLastname(userInfo.getLastname());
        user.setEmail(userInfo.getEmail());
        user.setDateOfBirth(userInfo.getDateOfBirth());
        user.setPassword(userInfo.getPassword());
        user.setPhoneNumber(userInfo.getPhoneNumber());
        if(this.userService.save(user) != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }

    }
}
