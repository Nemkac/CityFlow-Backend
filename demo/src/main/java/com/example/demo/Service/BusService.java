package com.example.demo.Service;

import com.example.demo.DTO.AddRoutesToBusDTO;
import com.example.demo.DTO.EditBusDTO;
import com.example.demo.Exceptions.BusNotFoundException;
import com.example.demo.Model.Bus;
import com.example.demo.Model.Route;
import com.example.demo.Repository.BusRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
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

    public void edit(EditBusDTO dto) {

    }

    public void addRoutesToBus(AddRoutesToBusDTO dto){
        dto.getBus().getRoutes().addAll(dto.getRoutes());

        for (Route route : dto.getRoutes()) {
            if(dto.isScaleDepartureTime()) {
                if(route.getDepartureFromStartingStation() > 10) {
                    this.routeService.updateDepartureFromStartingStation(route);
                } else {
                    if(route.getDepartureFromStartingStation() == 0) {
                        this.routeService.updateDepartureFromStartingStation(route);
                    } else {
                        route.setDepartureFromStartingStation(10);
                    }
                }
            }

            if (dto.isExtendClosingTime()) {
                this.routeService.extendClosingTime(route);
            }
        }

        this.save(dto.getBus());
    }

    public List<Bus> findAll(){
        return this.busRepository.findAll();
    }

    @Transactional
    public void deleteBus(Integer id){
        Bus bus = this.findById(id);

        if (bus.getRoutes() == null || bus.getRoutes().isEmpty()) {
            busRepository.delete(bus);
            System.out.println("Bus deleted with no routes.");
        } else {
            for (Route route : bus.getRoutes()) {
                route.getBuses().remove(bus);

                if (route.getBuses().isEmpty()) {
                    route.setDepartureFromStartingStation(0);
                } else {
                    this.routeService.updateDepartureFromStartingStation(route);
                }

                // Save the updated route
                this.routeService.save(route);
            }

            busRepository.delete(bus);
            System.out.println("Bus deleted and departure times updated for affected routes.");
        }
    }

    public Bus findById(Integer id){
        Optional<Bus> optionalBus = busRepository.findById(id);
        return optionalBus.orElseThrow(() -> new BusNotFoundException("Bus could not be found by given ID"));
    }

    public List<Bus> findAllById(List<Integer> ids){
        return this.busRepository.findAllById(ids);
    }
}
