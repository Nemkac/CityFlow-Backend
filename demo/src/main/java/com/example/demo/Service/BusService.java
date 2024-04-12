package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private ServiceUrgencyRankingsService serviceUrgencyRankingsService;

    public Bus save(Bus bus) {
        if(this.busRepository.save(bus) != null) {
            this.serviceUrgencyRankingsService.createRankings();
            return bus;
        }
        return null;
        //return this.busRepository.save(bus);
    }
    public Bus getById(Integer id) { return this.busRepository.getByBusId(id); }

    public List<Bus> getAll() { return this.busRepository.findAll(); }

    public boolean ifExists(Integer chassisNumber) {
        for(Bus bus : getAll()) {
            if(bus.getChassisNumber() == chassisNumber) {
                return true;
            }
        }
        return false;
    }

}
