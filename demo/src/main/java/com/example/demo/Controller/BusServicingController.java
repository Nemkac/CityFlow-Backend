package com.example.demo.Controller;


import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.BusServicing;
import com.example.demo.Service.BusService;
import com.example.demo.Service.BusServicingService;
import com.example.demo.Service.ServiceUrgencyRankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusServicingController {

    @Autowired
    private BusServicingService busServicingService;

    @Autowired
    private BusService busService;

    @Autowired
    private ServiceUrgencyRankingsService serviceUrgencyRankingsService;

    @PostMapping(consumes = "application/json", value="/CityFlow/addBusServicing")
    public ResponseEntity<BusServicing> addBus(@RequestBody BusServicing busServicing) {
        if(busServicingService.save(busServicing) == null){
            return new ResponseEntity("Bus servicing already exists", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<BusServicing>(busServicing,HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/getBusServicing/{id}")
    public ResponseEntity<BusServicing> getAllServicings(@PathVariable Integer id){
        BusServicing busServicing = busServicingService.getById(id);
        if(busServicing!=null) {
            return new ResponseEntity<BusServicing>(busServicing, HttpStatus.OK);
        }
        return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(consumes = "application/json",value = "/CityFlow/getBusesSortedServicingsByDate")
    public ResponseEntity<List<BusServicing>> getSortedServicings(@RequestBody Bus bus){
        return new ResponseEntity<List<BusServicing>>(this.busServicingService.findAllByBusOrderByDateDesc(bus),HttpStatus.OK);
    }


}
