package com.example.demo.Service;

import com.example.demo.DTO.AddBusToRouteDTO;
import com.example.demo.Exceptions.RouteNotFoundException;
import com.example.demo.Model.Bus;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Model.Station;
import com.example.demo.Repository.BusRepository;
import com.example.demo.Repository.RouteRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private BusService busService;

    public Route save(Route route){
        return routeRepository.save(route);
    }

    public Route findById(Integer id){
        Optional<Route> optionalRoute = routeRepository.findById(id);
        return optionalRoute.orElseThrow(() -> new RouteNotFoundException("Route could not be found by given ID"));
    }
    public Route getById(int id) {
        return routeRepository.findById(id).orElse(null);
    }

    public void deleteBusFromRoute(Integer routeId, Integer busId){
        Route selectedRoute = findById(routeId);
        Bus busToRemove = this.busService.findById(busId);
        List<Bus> routeBuses = selectedRoute.getBuses();
        List<Route> busRoutes = busToRemove.getRoutes();

        routeBuses.removeIf(bus -> bus.id.equals(busId));
        busRoutes.removeIf(route -> route.id.equals(routeId));

        //save(selectedRoute);
//        if(selectedRoute.departureFromStartingStation > 10){
//            updateDepartureFromStartingStation(selectedRoute);
//        }

        if(routeBuses.isEmpty()) {
            selectedRoute.setDepartureFromStartingStation(0);
            this.save(selectedRoute);
        } else {
            updateDepartureFromStartingStation(selectedRoute);
        }

        this.busService.save(busToRemove);

        //        if(selectedRoute != null && busToRemove != null){
//            List<Bus> routeBuses = selectedRoute.getBuses();
//            List<Route> busRoutes = busToRemove.getRoutes();
//
//            routeBuses.removeIf(bus -> bus.id.equals(busId));
//            busRoutes.removeIf(route -> route.id.equals(routeId));
//
//            //save(selectedRoute);
//            updateDepartureFromStartingStation(selectedRoute);
//            this.busService.save(busToRemove);
//        }
    }

    public List<Route> getAll(){
        return routeRepository.findAll();
    }

    @Transactional
    public void deleteById(Integer routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);

        if (optionalRoute.isPresent()) {
            Route route = optionalRoute.get();

            for (Bus bus : route.getBuses()) {
                bus.getRoutes().remove(route);
                this.busService.save(bus);
            }

            route.getBuses().clear();

            routeRepository.deleteById(routeId);

            System.out.println("Route and its associations deleted successfully.");
        } else {
            System.out.println("Route with id " + routeId + " not found.");
        }
    }
    public boolean existsByName(String name) {
        return this.routeRepository.existsByName(name);
    }

    public void updateDepartureFromStartingStation(Route route) {
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
        System.out.println("New Departure Time: " + newDeparture);
        if(newDeparture < 10) {
            route.setDepartureFromStartingStation(10);
        } else {
            route.setDepartureFromStartingStation(newDeparture);
        }

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
        double distance = 6371 * c;

        return distance;
    }

    @Transactional
    public void addBusesToRoute(AddBusToRouteDTO dto) {
        Route route = this.findById(dto.getSelectedRoute().getId());

        List<Bus> buses = dto.getSelectedBuses().stream()
                .map(bus -> busService.findById(bus.getId()))
                .collect(Collectors.toList());

        for (Bus bus : buses) {
            if (!route.getBuses().contains(bus)) {
                route.getBuses().add(bus);
                bus.getRoutes().add(route);
            }
        }

        if (dto.isScaleDepartureTime()) {
            if (route.getDepartureFromStartingStation() > 10 || route.getDepartureFromStartingStation() == 0) {
                this.updateDepartureFromStartingStation(route);
            } else {
                route.setDepartureFromStartingStation(10);
            }
        }

        if (dto.isExtendClosingTime()) {
            this.extendClosingTime(route);
        }

        this.routeRepository.save(route);
        buses.forEach(this.busService::save);
    }




    public void extendClosingTime(Route route) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime closingTime = LocalTime.parse(route.getClosingTime(), formatter);
        closingTime = closingTime.plusMinutes(30);
        route.setClosingTime(closingTime.format(formatter));
    }
}
