package com.example.demo.Controller;

import com.example.demo.Model.Route;
import com.example.demo.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/CityFlow")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/allRoutes")
    public ResponseEntity<List<Route>> getAll(){
        List<Route> routes = routeService.getAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }

    @GetMapping(path = "/route/{id}")
    public ResponseEntity<Route> getCompanyById(@PathVariable Integer id) {
        List<Route> routes = routeService.getAll();

        for (Route route : routes) {
            int routeId = route.getId();
            if (routeId == id) {
                return new ResponseEntity<>(route, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
