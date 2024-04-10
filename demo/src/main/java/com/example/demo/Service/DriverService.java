package com.example.demo.Service;

import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
//import com.example.demo.Repository.DriverRepository;
import com.example.demo.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;
    public Driver save(Driver driver){
        return this.driverRepository.save(driver);
    }
    public void delete(Driver driver) {
        driverRepository.delete(driver);
    }
    public Driver getByUser(User user) {
        return driverRepository.findByUser(user);
    }
}
