package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route save(Route route){
        return routeRepository.save(route);
    }

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
        System.out.println("Prvi poziv u servisu");
        Route route = findById(routeId);
        System.out.println(route.name);
        if(route != null){
            List<Bus> routeBuses = route.getBuses();
            System.out.println("Lista dobijena");
            for(Bus bus : routeBuses){
                if(bus.id.equals(busId)){
                    System.out.println("Bus pronadjen");
                    routeBuses.remove(bus);
                    System.out.println("Bus obrisan");
                    route.setBuses(routeBuses);
                    this.routeRepository.save(route);
                }
            }
        }
    }
    public Route getByEndPoint(Location endPoint){
        return routeRepository.getByEndPoint(endPoint);
    }

    public List<Route> getAll(){
        return routeRepository.findAll();
    }

    public void deleteById(Integer id){
        routeRepository.deleteById(id);
    }
}
