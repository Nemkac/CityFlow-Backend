package com.example.demo.Controller;

import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.Driver;
import com.example.demo.Service.BusMalfunctionReportService;
import com.example.demo.Service.BusService;
import com.example.demo.Service.DriverService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}