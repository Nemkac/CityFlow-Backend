package com.example.demo.Controller;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Driver;
import com.example.demo.Service.BusService;
import com.example.demo.Service.DriverService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BusController {
    @Autowired
    private BusService busService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;



    // za testiranje, brise se posle
    @GetMapping(value = "/CityFlow/testBus")
    public ResponseEntity<Bus> testBus() {
        Bus bus = new Bus();
        bus.setManufacturer("Mann");
        bus.setModel("CFXD");
        bus.setModelYear(2019);
        bus.setSeatingCapacity(69);
        bus.setChassisNumber(213948323);
        bus.setRegistrationNumber("KG265GT");
        bus.setCurrentMileage(12941);
        busService.save(bus);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value="/CityFlow/addBus")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        if(busService.ifExists(bus.getChassisNumber()) || busService.save(bus) == null){
            return new ResponseEntity("Bus already exists",HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Bus>(bus,HttpStatus.OK);
    }


}