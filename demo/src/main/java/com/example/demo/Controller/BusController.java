package com.example.demo.Controller;

import com.example.demo.DTO.BusDTO;
import com.example.demo.Model.Bus;
import com.example.demo.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/CityFlow")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping(path = "/buses")
    public ResponseEntity<List<Bus>> findAll(){
        try {
            List<Bus> buses = busService.findAll();
            return new ResponseEntity<>(buses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/saveBus")
    public ResponseEntity<Bus> save(@RequestBody BusDTO newBus){
        try{
            Bus bus = new Bus();

            bus.setLicencePlate(newBus.getLicencePlate());
            bus.setRoutes(newBus.getRoutes());

            busService.save(bus);

            return new ResponseEntity<>(bus, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }
}
