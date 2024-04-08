package com.example.demo.Service;

import com.example.demo.Model.Driver;
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
    public Driver getById(Integer id) { return this.driverRepository.getByDriverId(id); }

}
