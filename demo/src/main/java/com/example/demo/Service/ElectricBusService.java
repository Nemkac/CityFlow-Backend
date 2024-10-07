package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Model.ElectricBus;
import com.example.demo.Repository.ElectricBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectricBusService {
    @Autowired
    private ElectricBusRepository electricBusRepository;

    public ElectricBus save(ElectricBus bus){
        return this.electricBusRepository.save(bus);
    }

    public boolean existsByBus(Bus bus) {
        return this.electricBusRepository.existsByBus(bus);
    }
}
