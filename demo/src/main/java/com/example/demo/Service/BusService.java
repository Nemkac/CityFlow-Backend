package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus save(Bus bus) { return this.busRepository.save(bus); }
    public Bus getById(Integer id) { return this.busRepository.getByBusId(id); }

}
