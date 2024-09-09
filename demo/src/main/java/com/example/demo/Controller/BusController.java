package com.example.demo.Controller;

import com.example.demo.DTO.AddRoutesToBusDTO;
import com.example.demo.DTO.BusDTO;
import com.example.demo.DTO.EditBusDTO;
import com.example.demo.Model.Bus;
import com.example.demo.Model.Route;
import com.example.demo.Service.BusService;
import com.example.demo.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/get/all")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<List<Bus>> findAll(@RequestHeader("Authorization") String authorization){
        try {
            List<Bus> buses = busService.findAll();
            return new ResponseEntity<>(buses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/save")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<Bus> save(@RequestHeader("Authorization") String authorization, @RequestBody BusDTO newBus){
        try{
            Bus bus = new Bus();
            bus.setLicencePlate(newBus.getLicencePlate());

            List<Route> selectedRoutes = new ArrayList<>(newBus.getRoutes());
            bus.setRoutes(newBus.getRoutes());

            for (Route route : selectedRoutes) {
                if(route.getBuses().isEmpty()){
                    routeService.updateDepartureFromStartingStation(route);
                }
            }

            busService.save(bus);
            return new ResponseEntity<>(bus, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<String>deleteBus(@RequestHeader("Authorization") String authorization, @PathVariable Integer id){
        try {
//            Bus busToDelete = this.busService.findById(id);
//            busService.deleteById(busToDelete.id);
            busService.deleteBus(id);
            return new ResponseEntity<>("Bus deldeted successfully!", HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>("Error while accessing deleting logics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/routes/add")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<String> addRoutesToBus(@RequestHeader("Authorization") String authorization, @RequestBody AddRoutesToBusDTO dto){
        try{
            this.busService.addRoutesToBus(dto);
            return new ResponseEntity<>("Routes successfully added!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error while accessing routes addition logic", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/update")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<String> edit(@RequestHeader("Authorization") String authorization, @RequestBody EditBusDTO dto){
        try{
            Bus bus = this.busService.findById(dto.getBus().getId());
            if(bus != null) {
                bus.setLicencePlate(dto.getLincencePlate());
                this.busService.save(bus);
                return new ResponseEntity<>("Licence plate successfully edited", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Bus not found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error while accessing editing logic", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
