package com.example.demo.Service;

import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import com.example.demo.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserService userService;

    public Driver save(Driver driver) {
        return this.driverRepository.save(driver);
    }

    public Driver getById(Integer id) {
        return this.driverRepository.getByDriverId(id);
    }

    public List<Driver> findAll() {
        return this.driverRepository.findAll();
    }

    public Driver addDriver(Driver driver){
        return this.driverRepository.save(driver);
    }

    public boolean ifExists(Driver driver) {
        List<Driver> drivers = findAll();
        for(Driver foundDriver : drivers) {
            if(Objects.equals(driver.getUser(),foundDriver.getUser())){
                return true;
            }
        }
        return false;
    }

    public Driver getByUserUsername(String username){
        return this.driverRepository.getDriverByUser(this.userService.FindByUsername(username));
    }

    public Driver addDriverByUserId(Integer userId){
        User user = this.userService.getUserById(userId);
        if(user != null) {
            Driver driver = new Driver(user);
            this.save(driver);
            return driver;
        }
        return null;
    }
}