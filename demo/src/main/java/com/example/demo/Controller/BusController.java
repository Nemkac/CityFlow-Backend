package com.example.demo.Controller;

import com.example.demo.Model.Bus;
import com.example.demo.Service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
