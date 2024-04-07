package com.example.demo.Controller;

import com.example.demo.Service.DriverService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/CityFlow/driver")
public class DriverController {
    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

}
