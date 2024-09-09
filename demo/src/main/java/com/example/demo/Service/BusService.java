package com.example.demo.Service;

import com.example.demo.Exceptions.BusNotFoundException;
import com.example.demo.Model.Bus;
import com.example.demo.Model.Route;
import com.example.demo.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteService routeService;

    public Bus save(Bus bus){
        Bus savedBus = busRepository.save(bus);
        return savedBus;
    }

    public List<Bus> findAll(){
        return this.busRepository.findAll();
    }

    public void deleteById(Integer id) {
        Optional<Bus> bus = this.busRepository.findById(id);
        List<Route> routes = bus.get().getRoutes();

        for(Route route : routes){
            this.routeService.updateDepartureFromStartingStation(route);
        }

        this.busRepository.deleteById(id);
    }

    public Bus findById(Integer id){
        Optional<Bus> optionalBus = busRepository.findById(id);
        return optionalBus.orElseThrow(() -> new BusNotFoundException("Bus could not be found by given ID"));
    }

    public List<Bus> findAllById(List<Integer> ids){
        return this.busRepository.findAllById(ids);
    }
}
