package com.example.demo.Controller;


import com.example.demo.Model.BusServicing;
import com.example.demo.Model.ServiceUrgencyRankings;
import com.example.demo.Service.BusService;
import com.example.demo.Service.BusServicingService;
import com.example.demo.Service.ServiceUrgencyRankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
