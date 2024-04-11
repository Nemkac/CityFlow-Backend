package com.example.demo.Controller;


import com.example.demo.Model.Bus;
import com.example.demo.Model.BusServicing;
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


}
