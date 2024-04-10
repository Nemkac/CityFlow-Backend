package com.example.demo.Controller;

import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import com.example.demo.Service.BusMalfunctionReportService;
import com.example.demo.Service.BusService;
import com.example.demo.Service.DriverService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {
    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private BusService busService;

    @Autowired
    private BusMalfunctionReportService busMalfunctionReportService;


    @GetMapping(value="/CityFlow/testDriver")
    public ResponseEntity<Driver> testDriver() {
        Driver driver = new Driver(1);
        driverService.save(driver);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/testReport")
    public ResponseEntity<BusMalfunctionReport> testReport() {
        BusMalfunctionReport busMalfunctionReport = new BusMalfunctionReport(driverService.getById(1), busService.getById(1));
        busMalfunctionReportService.save(busMalfunctionReport);
        return new ResponseEntity<>(busMalfunctionReport, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json", value ="/CityFlow/addDriver")
    public ResponseEntity<Driver> addDriver(@RequestBody Driver driver) {
        if(driverService.ifExists(driver)){
            return new ResponseEntity("User already exist",HttpStatus.FORBIDDEN);
        }
        this.driverService.save(driver);
        return new ResponseEntity<Driver>(driver,HttpStatus.OK);
    }

    @PostMapping(value ="/CityFlow/addDriver/{userId}")
    public ResponseEntity<Driver> addDriverByUserId(@PathVariable Integer userId) {
        if(driverService.ifUserIsDriver(userId)){
            return new ResponseEntity("Driver already exists",HttpStatus.FORBIDDEN);
        }
        Driver driver = this.driverService.addDriverByUserId(userId);
        this.driverService.save(driver);
        return new ResponseEntity<Driver>(driver,HttpStatus.OK);
    }

    @PostMapping(value ="/CityFlow/addDriver/test")
    public ResponseEntity<Driver> testAddDriver() {
        if(driverService.ifExists(this.driverService.getById(1))){
            return new ResponseEntity("Driver already exists",HttpStatus.FORBIDDEN);
        }
        Driver driver = this.driverService.addDriverByUserId(1);
        this.driverService.save(driver);
        return new ResponseEntity<Driver>(driver,HttpStatus.OK);
    }










}
