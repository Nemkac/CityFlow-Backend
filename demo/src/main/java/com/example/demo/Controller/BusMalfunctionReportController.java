package com.example.demo.Controller;

import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.Driver;
import com.example.demo.Service.BusMalfunctionReportService;
import com.example.demo.Service.BusService;
import com.example.demo.Service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusMalfunctionReportController {
    @Autowired
    private BusMalfunctionReportService busMalfunctionReportService;

    @Autowired
    private BusService busService;

    @Autowired
    private DriverService driverService;

    @PostMapping(consumes = "application/json", value="/CityFlow/reportMalfunction")
    public ResponseEntity<BusMalfunctionReport> createReport(@RequestBody BusMalfunctionReport report){
        if(busMalfunctionReportService.save(report)==null){
            return new ResponseEntity("Cannot create report", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(report, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value="/CityFlow/reportMalfunctionViaDriver")
    public ResponseEntity<BusMalfunctionReport> createReportViaDriver(@RequestBody Driver driver){
        BusMalfunctionReport report = new BusMalfunctionReport(driver,driver.getBus());
        if(busMalfunctionReportService.save(report)==null){
            return new ResponseEntity("Cannot create report", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(report, HttpStatus.OK);
    }


    @PostMapping(value="/CityFlow/reportMalfunctionViaDriverId/{driverId}")
    public ResponseEntity<BusMalfunctionReport> createReportViaDriverId(@PathVariable Integer driverId){
        Driver driver = this.driverService.getById(driverId);
        BusMalfunctionReport report = new BusMalfunctionReport(driver,driver.getBus());
        if(busMalfunctionReportService.save(report)==null){
            return new ResponseEntity("Cannot create report", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(report, HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/getAllReports")
    public ResponseEntity<List<BusMalfunctionReport>> getAllReports(){
        if(!busMalfunctionReportService.findAll().isEmpty()) {
            return new ResponseEntity<List<BusMalfunctionReport>>(busMalfunctionReportService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity("No reports found", HttpStatus.NOT_FOUND);
    }

    // vidi zasto ovo ne radi, radi sa driverom
    @GetMapping(consumes = "application/json",value="/CityFlow/getAllReportsByBus")
    public ResponseEntity<List<BusMalfunctionReport>> getAllReportsByBus(@RequestBody Bus bus){
        if(!busMalfunctionReportService.getAllByBus(bus).isEmpty()) {
            return new ResponseEntity<List<BusMalfunctionReport>>(busMalfunctionReportService.getAllByBus(bus), HttpStatus.OK);
        }
        return new ResponseEntity("No reports found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(consumes = "application/json",value="/CityFlow/getAllReportsByDriver")
    public ResponseEntity<List<BusMalfunctionReport>> getAllReportsByDriver(@RequestBody Driver driver){
        if(!busMalfunctionReportService.getAllByDriver(driver).isEmpty()) {
            return new ResponseEntity<List<BusMalfunctionReport>>(busMalfunctionReportService.getAllByDriver(driver), HttpStatus.OK);
        }
        return new ResponseEntity("No reports found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/CityFlow/getAllProcessedReports")
    public ResponseEntity<List<BusMalfunctionReport>> getAllProcessedReports(){
        if(!busMalfunctionReportService.getAllProcessed().isEmpty()) {
            return new ResponseEntity<List<BusMalfunctionReport>>(busMalfunctionReportService.getAllProcessed(), HttpStatus.OK);
        }
        return new ResponseEntity("No reports found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/CityFlow/getAllUnprocessedReports")
    public ResponseEntity<List<BusMalfunctionReport>> getAllUnprocessedReports(){
        if(!busMalfunctionReportService.getAllUnprocessed().isEmpty()) {
            return new ResponseEntity<List<BusMalfunctionReport>>(busMalfunctionReportService.getAllUnprocessed(), HttpStatus.OK);
        }
        return new ResponseEntity("No reports found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/CityFlow/testReportOnFront")
    public ResponseEntity<BusMalfunctionReport> testReportOnFront(){
        BusMalfunctionReport report = new BusMalfunctionReport(this.driverService.getById(1),this.busService.getById(1));
        this.busMalfunctionReportService.save(report);
        return new ResponseEntity<BusMalfunctionReport>(report, HttpStatus.OK);

    }



}
