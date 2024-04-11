package com.example.demo.Service;

import com.example.demo.Model.ServiceUrgencyRankings;
import com.example.demo.Repository.ServiceUrgencyRankingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUrgencyRankingsService {

    @Autowired
    private ServiceUrgencyRankingsRepository serviceUrgencyRankingsRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private BusServicingService busServicingService;

    public ServiceUrgencyRankings save(ServiceUrgencyRankings serviceUrgencyRankings) { return this.serviceUrgencyRankingsRepository.save(serviceUrgencyRankings); }

    public ServiceUrgencyRankings getById(Integer id) { return this.serviceUrgencyRankingsRepository.getServiceUrgencyRankingsById(id); }

    public List<ServiceUrgencyRankings> findAll(){ return this.serviceUrgencyRankingsRepository.findAll(); }
}
