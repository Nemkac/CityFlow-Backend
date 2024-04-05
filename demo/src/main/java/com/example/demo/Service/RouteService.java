package com.example.demo.Service;

import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route save(Route route){
        return routeRepository.save(route);
    }

    public Route getById(int  id){
        return routeRepository.getById(id);
    }

    public Route getByStartingPoint(Location startingPoint){
        return routeRepository.getByStartingPoint(startingPoint);
    }

    public Route getByEndPoint(Location endPoint){
        return routeRepository.getByEndPoint(endPoint);
    }

    public List<Route> getAll(){
        return routeRepository.findAll();
    }

    public void delete(Route route){
        routeRepository.delete(route);
    }
}
