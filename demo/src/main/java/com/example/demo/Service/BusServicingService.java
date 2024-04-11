package com.example.demo.Service;

import com.example.demo.Model.BusServicing;
import com.example.demo.Repository.BusServicingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusServicingService {

    @Autowired
    private BusServicingRepository busServicingRepository;

    @Autowired
    private BusService busService;

    public BusServicing save(BusServicing busServicing) { return this.busServicingRepository.save(busServicing); }

    public BusServicing getById(Integer id) { return this.busServicingRepository.getBusServicingById(id); }
}