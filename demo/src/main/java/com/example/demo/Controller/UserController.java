package com.example.demo.Controller;


import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value="/CityFlow/getAllUsersNoAdmin")
    public ResponseEntity<List<User>> getAllUsersNoAdmin(){
        if(!this.userService.getAllUsersNoAdmin().isEmpty()){
            return new ResponseEntity<>(this.userService.getAllUsersNoAdmin(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/CityFlow/changeRoleVoid/{userId}/{newRoleInt}")
    @ResponseStatus(HttpStatus.OK)
    public void changeRoleVoid(@PathVariable Integer userId, @PathVariable Integer newRoleInt){
        // 0 -> NO_ROLE
        // 1 -> ROLE_DRIVER
        // 2 -> ROLE_SERVICE
        // 3 -> ROLE_CHARGER
        // 4 -> SUPER_ADMIN
        String newRole = new String();
        if(newRoleInt == 1) {
            newRole = "ROLE_DRIVER";
        } else if (newRoleInt == 2) {
            newRole = "ROLE_SERVICE";
        } else if (newRoleInt == 3) {
            newRole = "ROLE_CHARGER";
        } else if (newRoleInt == 0) {
            newRole = "NO_ROLE";
        }
        this.userService.getUserById(userId).setRoles(newRole);
        this.userService.save(this.userService.getUserById(userId));
        //return new ResponseEntity<>(this.userService.getUserById(userId).getRoles(),HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/changeRole/{userId}/{newRoleInt}")
    public ResponseEntity<User> changeRole(@PathVariable Integer userId, @PathVariable Integer newRoleInt){
        // 0 -> NO_ROLE
        // 1 -> ROLE_DRIVER
        // 2 -> ROLE_SERVICE
        // 3 -> ROLE_CHARGER
        // 4 -> SUPER_ADMIN
        String newRole = new String();
        if(newRoleInt == 1) {
            newRole = "ROLE_DRIVER";
        } else if (newRoleInt == 2) {
            newRole = "ROLE_SERVICE";
        } else if (newRoleInt == 3) {
            newRole = "ROLE_CHARGER";
        } else if (newRoleInt == 0) {
            newRole = "NO_ROLE";
        }
        this.userService.getUserById(userId).setRoles(newRole);
        this.userService.save(this.userService.getUserById(userId));
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
    }

 }
