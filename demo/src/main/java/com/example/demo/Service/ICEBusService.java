package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Model.ICEBus;
import com.example.demo.Repository.ICEBusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICEBusService {
    @Autowired
    private ICEBusRepository iceBusRepository;

    public ICEBus save(ICEBus bus) {
        return this.iceBusRepository.save(bus);
    }

    public boolean existsByBus(Bus bus) {
        return this.iceBusRepository.existsByBus(bus);
    }
}
