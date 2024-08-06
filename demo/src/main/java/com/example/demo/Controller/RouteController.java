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
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/CityFlow")
@RequiredArgsConstructor
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private LocationService locationService;

//    private final WebClient webClient;

//    private void searchRouteGraph(SearchDTO dto) {
//        webClient.post()
//                .uri("http://localhost:8080/api/users/searchRoute")
//                .bodyValue(dto)
//                .retrieve()
//                .bodyToMono(SearchDTO.class)
//                .subscribe(
//                        result -> System.out.println("Search saved in graph database"),
//                        error -> System.err.println("Failed to save search in graph database: " + error.getMessage())
//                );
//    }

//    private String mostFrequentedRoute(String username){
//        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/users/mostFrequented/{username}")
//                .buildAndExpand(username)
//                .toUri();
//
//        return webClient.get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(String.class).block();
//    }

//    @GetMapping(path = "/mostFrequented/{username}")
//    public ResponseEntity<String> getMostFrequentedRoute(@PathVariable String username){
//        String routeName = mostFrequentedRoute(username);
//        return new ResponseEntity<>(routeName, HttpStatus.OK);
//    }

    @GetMapping(path = "/allRoutes")
    public ResponseEntity<List<Route>> getAll(){
        List<Route> routes = routeService.getAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(path = "/getStations")
    public ResponseEntity<List<Location>> getAllStations() {
        List<Location> locations = locationService.getAll();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping(path = "/station/save")
    public ResponseEntity<Location> saveStation(@RequestBody Location location){
        Location existingLocation = findOrCreateLocation(location);
        if(existingLocation != null) {
            this.locationService.save(location);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
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

//    @PostMapping(path = "/route/search")
//    public void searchRoute(@RequestBody SearchDTO dto){
//        searchRouteGraph(dto);
//    }

    @PostMapping(path = "/saveRoute")
    public ResponseEntity<Route> save(@RequestBody RouteDTO routeDTO) {
        Location starting = findOrCreateLocation(routeDTO.getStartingPoint());
        Location ending = findOrCreateLocation(routeDTO.getEndingPoint());

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

    public Location findOrCreateLocation(Location location) {
        Location existingLocation = this.locationService.getByLatitudeAndLongitude(location.latitude, location.longitude);
        return (existingLocation != null) ? existingLocation : this.locationService.save(location);
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

//    public List<String> atLeastThreeStations() {
//        return webClient.get()
//                .uri("http://localhost:8080/api/routes/atLeastThreeStations")
//                .retrieve()
//                .bodyToFlux(String.class)
//                .collectList()
//                .block();
//    }

//    @GetMapping(path = "/atLeastThreeStations")
//    public ResponseEntity<List<String>> getRouteWithAtLeastThreeStations(){
//        List<String> routeNames = atLeastThreeStations();
//        return new ResponseEntity<>(routeNames, HttpStatus.OK);
//    }

//    private Integer stationsCount(String routeName){
//        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/routes/{routeName}/stations/count")
//                .buildAndExpand(routeName)
//                .toUri();
//
//        return webClient.get()
//                .uri(uri)
//                .retrieve()
//                .bodyToMono(Integer.class).block();
//    }

//    @CrossOrigin(origins = "http://localhost:4200")
//    @GetMapping(path = "/stationsCount/{routeName}")
//    public ResponseEntity<Integer> getRouteWithAtLeastThreeStations(@PathVariable String routeName){
//        Integer numberOfStation = stationsCount(routeName);
//        return new ResponseEntity<>(numberOfStation, HttpStatus.OK);
//    }

//    public List<String> getPopularRoutes() {
//        return webClient.get()
//                .uri("http://localhost:8080/api/routes/popular")
//                .retrieve()
//                .bodyToFlux(String.class)
//                .collectList()
//                .block();
//    }

//    @GetMapping(path = "/popular")
//    public ResponseEntity<List<String>> getMostPopularRoutes(){
//        List<String> popularRoutes = getPopularRoutes();
//        return new ResponseEntity<>(popularRoutes, HttpStatus.OK);
//    }

//    public String getLongestRouteName() {
//        return webClient.get()
//                .uri("http://localhost:8080/api/routes/longest")
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//    }

//    @GetMapping(path = "/longest")
//    public ResponseEntity<String> getLongestRoute(){
//        String routeName = getLongestRouteName();
//        return new ResponseEntity<>(routeName, HttpStatus.OK);
//    }
}
