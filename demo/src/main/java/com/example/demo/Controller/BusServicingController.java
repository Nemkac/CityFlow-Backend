package com.example.demo.Controller;


import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.BusServicing;
import com.example.demo.Model.TimeSlot;
import com.example.demo.Service.BusService;
import com.example.demo.Service.BusServicingService;
import com.example.demo.Service.ServiceUrgencyRankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    // ovo se brise
    @GetMapping(value="/CityFlow/testSortSlots")
    public ResponseEntity<List<TimeSlot>> testSortSlots(){
        TimeSlot timeSlot1 = new TimeSlot(LocalDate.now(),30);
        TimeSlot timeSlot2 = new TimeSlot(LocalDate.now().plusDays(12),45);
        TimeSlot timeSlot3 = new TimeSlot(LocalDate.now().minusDays(12),45);

        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        timeSlots.add(timeSlot1);
        timeSlots.add(timeSlot2);
        timeSlots.add(timeSlot3);
        return new ResponseEntity<>(this.serviceUrgencyRankingsService.sortTimeSlots(timeSlots),HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/testBookingServices")
    public ResponseEntity<List<BusServicing>> testBookingServices() {
        TimeSlot timeSlot1 = new TimeSlot(LocalDate.now(),30);
        TimeSlot timeSlot2 = new TimeSlot(LocalDate.now().plusDays(12),45);
        TimeSlot timeSlot3 = new TimeSlot(LocalDate.now().minusDays(12),45);
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        timeSlots.add(timeSlot1);
        timeSlots.add(timeSlot2);
        timeSlots.add(timeSlot3);
        return new ResponseEntity<>(this.serviceUrgencyRankingsService.bookServiceSlots(timeSlots),HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json",value="/CityFlow/bookServices")
    public ResponseEntity<List<BusServicing>> bookServices(@RequestBody List<TimeSlot> timeSlots) {
        List<BusServicing> bookedServices = this.serviceUrgencyRankingsService.bookServiceSlots(timeSlots);
        if(bookedServices != null) {
            return new ResponseEntity<>(bookedServices,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    }



}
