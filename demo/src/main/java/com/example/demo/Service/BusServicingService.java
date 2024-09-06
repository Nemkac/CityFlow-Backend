package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Model.BusServicing;
import com.example.demo.Repository.BusServicingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServicingService {

    @Autowired
    private BusServicingRepository busServicingRepository;

    @Autowired
    private BusService busService;

    public BusServicing save(BusServicing busServicing) { return this.busServicingRepository.save(busServicing); }

    public BusServicing getById(Integer id) { return this.busServicingRepository.getBusServicingById(id); }

    public List<BusServicing> getAll() { return this.busServicingRepository.findAll(); }

    public List<BusServicing> findAllByBusOrderByDateDesc (Bus bus) { return this.busServicingRepository.findAllByBusOrderByDateDesc(bus); }

    public void deleteById(Integer id) { this.busServicingRepository.deleteById(id); }

}
