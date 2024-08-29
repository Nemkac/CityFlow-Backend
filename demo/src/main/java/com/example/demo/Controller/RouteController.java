package com.example.demo.Controller;

import com.example.demo.DTO.DeleteBusFromRouteDTO;
import com.example.demo.DTO.RouteDTO;
import com.example.demo.DTO.SearchDTO;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Service.LocationService;
import com.example.demo.Service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/CityFlow")
@RequiredArgsConstructor
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private LocationService locationService;


    @GetMapping(path = "/allRoutes")
    public ResponseEntity<List<Route>> getAll(){
        List<Route> routes = routeService.getAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(path = "/route/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Integer id) {
        List<Route> routes = routeService.getAll();

        for (Route route : routes) {
            int routeId = route.getId();
            if (routeId == id) {
                return new ResponseEntity<>(route, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/saveRoute")
    public ResponseEntity<Route> save(@RequestBody RouteDTO routeDTO){

        Location starting = routeDTO.getStartingPoint();
        Location existingStarting = locationService.getByLatitudeAndLongiture(starting.latitude, starting.longitude);
        if(existingStarting == null){
            locationService.save(starting);
        }

        Location ending = routeDTO.getEndingPoint();
        Location existingEnding = locationService.getByLatitudeAndLongiture(ending.latitude, ending.longitude);
        if(existingEnding == null){
            locationService.save(ending);
        }

        List<Location> tmpStations = routeDTO.getStations();
        List<Location> stations = new ArrayList<>();

        for(Location station : tmpStations){
            Location savedStation = locationService.getByLatitudeAndLongiture(station.getLatitude(), station.getLongitude());
            if (savedStation == null) {
                locationService.save(station);
            } else {
                station = savedStation;
            }
            stations.add(station);
        }

        Route newRoute = new Route(
                routeDTO.getRouteName(),
                starting,
                stations,
                ending,
                routeDTO.getOpeningTime(),
                routeDTO.getClosingTime()
        );

        this.routeService.save(newRoute);
        return new ResponseEntity<>(newRoute, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteRoute/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable Integer id) {
        Route routeToDelete = this.routeService.findById(id);

        if (routeToDelete != null) {
            routeService.deleteById(routeToDelete.id);
            return ResponseEntity.ok().body("{\"message\": \"Route deleted successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Route not found\"}");
        }
    }

    @PutMapping(path = "/deleteBusFromRoute")
    public ResponseEntity<?> deleteBusFromRoute(@RequestBody DeleteBusFromRouteDTO dto){
        try{
            Integer routeId = dto.getRouteId();
            Integer busId = dto.getBusId();

            this.routeService.deleteBusFromRoute(routeId, busId);
            return new ResponseEntity<>("Bus successfuly removed from route!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }


}
