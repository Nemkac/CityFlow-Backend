package com.example.demo.Controller;

import com.example.demo.DTO.RouteDTO;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Service.LocationService;
import com.example.demo.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.http.HTTPException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/CityFlow")
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
}
