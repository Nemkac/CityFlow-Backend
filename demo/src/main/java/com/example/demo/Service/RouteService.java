package com.example.demo.Service;

import com.example.demo.DTO.RouteGraphDTO;
import com.example.demo.Model.Bus;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Model.Station;
import com.example.demo.Repository.BusRepository;
import com.example.demo.Repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusService busService;

//    private final WebClient webClient;


    public Route save(Route route){

//        RouteGraphDTO routeGraph = new RouteGraphDTO();
//
//        routeGraph.setId(savedRoute.id);
//        routeGraph.setName(savedRoute.name);
//        routeGraph.setBuses(savedRoute.buses);
//
//        List<Location> locations = new ArrayList<>();
//        List<Station> stations = new ArrayList<>();
//
//        locations.add(savedRoute.startingPoint);
//        locations.addAll(savedRoute.stations);
//        locations.add(savedRoute.endPoint);
//
//        for(Location loc : locations){
//            Station station = new Station();
//            station.setLocation(loc);
//            stations.add(station);
//        }
//        routeGraph.setLocations(locations);
//        routeGraph.setStations(stations);

//        sendRouteToGraphDatabase(routeGraph);
        return routeRepository.save(route);
    }

//    private void sendRouteToGraphDatabase(RouteGraphDTO route) {
//        webClient.post()
//                .uri("http://localhost:8080/api/routes/save")
//                .bodyValue(route)
//                .retrieve()
//                .bodyToMono(RouteGraphDTO.class)
//                .subscribe(
//                        result -> System.out.println("Route saved in graph database with ID: " + result.getId()),
//                        error -> System.err.println("Failed to save route in graph database: " + error.getMessage())
//                );
//    }

    public Route findById(Integer id){
        Optional<Route> optionalRoute = routeRepository.findById(id);
        return optionalRoute.orElse(null);
    }
    public Route getById(int id) {
        return routeRepository.findById(id).orElse(null);
    }

    public Route getByStartingPoint(Location startingPoint){
        return routeRepository.getByStartingPoint(startingPoint);
    }
    public void deleteBusFromRoute(Integer routeId, Integer busId){
        Route selectedRoute = findById(routeId);
        Bus busToRemove = this.busService.findById(busId);
        if(selectedRoute != null && busToRemove != null){
            List<Bus> routeBuses = selectedRoute.getBuses();
            List<Route> busRoutes = busToRemove.getRoutes();

            routeBuses.removeIf(bus -> bus.id.equals(busId));
            busRoutes.removeIf(route -> route.id.equals(routeId));

            //save(selectedRoute);
            this.busService.save(busToRemove);
        }
    }
    public Route getByEndPoint(Location endPoint){
        return routeRepository.getByEndPoint(endPoint);
    }

    public List<Route> getAll(){
        return routeRepository.findAll();
    }

    public void deleteById(Integer id){
//        Route route = findById(id);
        routeRepository.deleteById(id);
//        deleteRouteInGraphDatabase(route.name);
    }
//    private void deleteRouteInGraphDatabase(String routeName) {
//        webClient.delete()
//                .uri(uriBuilder -> uriBuilder.scheme("http")
//                        .host("localhost")
//                        .port(8080)
//                        .path("/api/routes/deleteByName")
//                        .queryParam("name", routeName)
//                        .build())
//                .retrieve()
//                .bodyToMono(Void.class)
//                .subscribe(
//                        result -> System.out.println("Route deleted in graph database: " + routeName),
//                        error -> System.err.println("Failed to delete route in graph database: " + error.getMessage())
//                );
//    }

    public void updateDepartureFromStartingStation(Route route, Bus bus) {
        double totalDistance = 0;
        Location previous = route.getStartingPoint();
        for (Location station : route.getStations()) {
            totalDistance += this.calculateDistance(previous, station);
            previous = station;
        }
        totalDistance += this.calculateDistance(previous, route.getEndPoint());

        int numberOfBuses = route.getBuses().size() + 1;
        double speed = 50;
        double travelTime = totalDistance / speed * 60;

        int newDeparture = (int) Math.ceil(travelTime / numberOfBuses);
        route.setDepartureFromStartingStation(newDeparture);
        this.save(route);
    }

    public double calculateDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.getLatitude());
        double lon1 = Math.toRadians(loc1.getLongitude());
        double lat2 = Math.toRadians(loc2.getLatitude());
        double lon2 = Math.toRadians(loc2.getLongitude());

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371 * c; // Radijus Zemlje u kilometrima

        return distance;
    }



}
