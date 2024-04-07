package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus save(Bus bus) { return this.busRepository.save(bus); }

}
