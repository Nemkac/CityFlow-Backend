package com.example.demo.Controller;

import com.example.demo.Model.Route;
import com.example.demo.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Route>> getAll(){
        List<Route> routes = routeService.getAll();
        return new ResponseEntity<>(routes, HttpStatus.OK);
    }


}
