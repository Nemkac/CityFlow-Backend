package com.example.demo.Service;

import com.example.demo.Model.ChargingStation;
import com.example.demo.Model.ElectricBus;
import com.example.demo.Repository.ElectricBusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricBusService {

    @Autowired
    private ElectricBusRepository electricBusRepository;

    public ElectricBus save(ElectricBus elBus){
        return this.electricBusRepository.save(elBus);
    }

    public ElectricBus getById(Integer id){
        return this.electricBusRepository.getByElBusId(id);
    }

    public List<ElectricBus> getAll() { return this.electricBusRepository.findAll(); }


}
