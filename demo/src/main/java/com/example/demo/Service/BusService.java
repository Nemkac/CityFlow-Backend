package com.example.demo.Service;
import com.example.demo.Model.Bus;
import com.example.demo.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus save(Bus bus){
        Bus savedBus = busRepository.save(bus);
        return savedBus;
    }

    public List<Bus> findAll(){
        return this.busRepository.findAll();
    }

    public void deleteById(Integer id) { this.busRepository.deleteById(id); }

    public Bus findById(Integer id){
        Optional<Bus> optionalBus = busRepository.findById(id);
        return optionalBus.orElse(null);
    }


}
