package com.example.demo.Controller;


import com.example.demo.Model.Bus;
import com.example.demo.Model.BusServicing;
import com.example.demo.Model.ServiceUrgencyRankings;
import com.example.demo.Service.BusService;
import com.example.demo.Service.BusServicingService;
import com.example.demo.Service.ServiceUrgencyRankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ServiceUrgencyRankingsController {
    @Autowired
    private ServiceUrgencyRankingsService serviceUrgencyRankingsService;

    @Autowired
    private BusService busService;

    @Autowired
    private BusServicingService busServicingService;

    @PostMapping(consumes = "application/json", value="/CityFlow/addServiceUrgencyRanking")
    public ResponseEntity<ServiceUrgencyRankings> addBus(@RequestBody ServiceUrgencyRankings serviceUrgencyRankings) {
        if(serviceUrgencyRankingsService.save(serviceUrgencyRankings) == null){
            return new ResponseEntity("Bus servicing already exists", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<ServiceUrgencyRankings>(serviceUrgencyRankings,HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/getServiceUrgencyRankings")
    public ResponseEntity<List<ServiceUrgencyRankings>> getAllServiceUrgencyRankings(){
        if(!serviceUrgencyRankingsService.findAll().isEmpty()) {
            return new ResponseEntity<List<ServiceUrgencyRankings>>(serviceUrgencyRankingsService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity("No rankings found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/CityFlow/testScoring")
    public ResponseEntity<List<ServiceUrgencyRankings>> getAllRankingsSortedByScores(){
        return new ResponseEntity<List<ServiceUrgencyRankings>>(this.serviceUrgencyRankingsService.rankingsSorted(), HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/testRanking")
    @ResponseStatus(HttpStatus.OK)
    public void sortRankings(){
        this.serviceUrgencyRankingsService.createRankings();
    }


    @GetMapping(value="/CityFlow/testChangingRank/{busId}/{ordinalNumber}")
    public ResponseEntity<List<ServiceUrgencyRankings>> testChangingRankings(@PathVariable Integer busId, @PathVariable Integer ordinalNumber){
        if(this.busService.getById(busId) == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Bus bus =  this.busService.getById(busId);
        return new ResponseEntity<List<ServiceUrgencyRankings>>(this.serviceUrgencyRankingsService.changeBusPriorityRanking(bus,ordinalNumber),HttpStatus.OK);

    }

}
