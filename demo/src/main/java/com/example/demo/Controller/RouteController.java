package com.example.demo.Controller;

import com.example.demo.DTO.DeleteBusFromRouteDTO;
import com.example.demo.DTO.RouteDTO;
import com.example.demo.DTO.SearchDTO;
import com.example.demo.Exceptions.DuplicateRouteException;
import com.example.demo.Exceptions.RouteCreatingException;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.LocationService;
import com.example.demo.Service.RouteService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/route")
@RequiredArgsConstructor
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/get/all")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<List<Route>> getAll(@RequestHeader("Authorization") String authorization){
        try {
            String bearerToken = authorization.substring(7);
            String username = jwtService.extractUsername(bearerToken);
            User user = userService.FindByUsername(username);
            if(user != null){
                List<Route> routes = routeService.getAll();
                return new ResponseEntity<>(routes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new  ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/stations/get/all")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<List<Location>> getAllStations(@RequestHeader("Authorization") String authorization) {
        List<Location> locations = locationService.getAll();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping(path = "/station/save")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<Location> saveStation(@RequestHeader("Authorization") String authorization, @RequestBody Location location){
        Location existingLocation = findOrCreateLocation(location);
        if(existingLocation != null) {
            this.locationService.save(location);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(path = "/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<Route> getRouteById(@RequestHeader("Authorization") String authorization, @PathVariable Integer id) {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        User user = userService.FindByUsername(username);
        Route route = routeService.findById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<Route> save(@RequestHeader("Authorization") String authorization, @RequestBody RouteDTO routeDTO) {
        try {
            Location starting = findOrCreateLocation(routeDTO.getStartingPoint());
            Location ending = findOrCreateLocation(routeDTO.getEndingPoint());

            if (routeService.existsByName(routeDTO.routeName)) {
                throw new DuplicateRouteException("A route with the same name already exists.");
            } else {
                Set<Location> uniqueStations = routeDTO.getStations().stream()
                        .map(this::findOrCreateLocation)
                        .collect(Collectors.toSet());

                Route newRoute = new Route(
                        routeDTO.getRouteName(),
                        starting,
                        new ArrayList<>(uniqueStations),
                        ending,
                        routeDTO.getOpeningTime(),
                        routeDTO.getClosingTime()
                );

                routeService.save(newRoute);
                return new ResponseEntity<>(newRoute, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new RouteCreatingException("Failed to create a new route: " + e.getMessage());
        }
    }

    public Location findOrCreateLocation(Location location) {
        Location existingLocation = this.locationService.getByLatitudeAndLongitude(location.latitude, location.longitude);
        return (existingLocation != null) ? existingLocation : this.locationService.save(location);
    }

    @DeleteMapping(path = "/deleteRoute/{id}")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<?> deleteRoute(@RequestHeader("Authorization") String authorization, @PathVariable Integer id) {
        Route routeToDelete = this.routeService.findById(id);

        if (routeToDelete != null) {
            routeService.deleteById(routeToDelete.id);
            return ResponseEntity.ok().body("{\"message\": \"Route deleted successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"message\": \"Route not found\"}");
        }
    }

    @PutMapping(path = "/deleteBusFromRoute")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<?> deleteBusFromRoute(@RequestHeader("Authorization") String authorization, @RequestBody DeleteBusFromRouteDTO dto){
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
