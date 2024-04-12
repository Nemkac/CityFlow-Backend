package com.example.demo.Service;

import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.BusServicing;
import com.example.demo.Model.ServiceUrgencyRankings;
import com.example.demo.Model.Bus;
import com.example.demo.Repository.ServiceUrgencyRankingsRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ServiceUrgencyRankingsService {

    @Autowired
    private ServiceUrgencyRankingsRepository serviceUrgencyRankingsRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private BusServicingService busServicingService;

    @Autowired
    private BusMalfunctionReportService busMalfunctionReportService;

    public ServiceUrgencyRankings save(ServiceUrgencyRankings serviceUrgencyRankings) { return this.serviceUrgencyRankingsRepository.save(serviceUrgencyRankings); }

    public ServiceUrgencyRankings getById(Integer id) { return this.serviceUrgencyRankingsRepository.getServiceUrgencyRankingsById(id); }

    public List<ServiceUrgencyRankings> findAll(){ return this.serviceUrgencyRankingsRepository.findAll(); }

    public ServiceUrgencyRankings getByBus(Bus bus) { return this.serviceUrgencyRankingsRepository.getServiceUrgencyRankingsByBus(bus); }

    public List<ServiceUrgencyRankings> rankingsSorted() { return this.serviceUrgencyRankingsRepository.findAllByOrderByScoreDesc(); }

    public void remove() { this.serviceUrgencyRankingsRepository.deleteAll(); }

    // ovo ce da se poziva svaki put kada se u bazu ubaci novi bus
    public void createRankings(){
        List<ServiceUrgencyRankings> allRankings = this.findAll();
        if(!allRankings.isEmpty()) {
            this.remove();
        }
        List<Bus> buses = busService.getAll();
        List<BusServicing> busServicings = busServicingService.getAll();
        Integer yearsOld = 0;
        Integer currentMileage = 0;
        Integer operationalImportance = 0;
        Long timeSinceLastService = 0L;
        Integer ifMalfunctionProcessed = 0;
        Integer mileageSinceLastService;
        Double formula = 0.0;
        double a = 0.1;
        double b = 0.25;
        double c = 0.2;
        double d = 0.15;
        double e = 0.3;
        for(Bus bus : buses){
            yearsOld = LocalDate.now().getYear() - bus.getModelYear();
            if(!this.busServicingService.findAllByBusOrderByDateDesc(bus).isEmpty()){
                timeSinceLastService = ChronoUnit.DAYS.between(this.busServicingService.findAllByBusOrderByDateDesc(bus).get(0).getDate(),LocalDate.now());
                mileageSinceLastService = bus.getCurrentMileage() - this.busServicingService.findAllByBusOrderByDateDesc(bus).get(0).getMileage();
            } else {
                timeSinceLastService = yearsOld * 365L;
                mileageSinceLastService = bus.getCurrentMileage();
            }
            currentMileage = bus.getCurrentMileage();
            if(!busMalfunctionReportService.getAllByBus(bus).isEmpty()) {
                ifMalfunctionProcessed = busMalfunctionReportService.getAllByBusSortByDate(bus).get(0).getIfProcessed() ? 0 : 100000;
            } else {
                ifMalfunctionProcessed = 0;
            }
            System.out.println("Bus broj : " + bus.getBusId());
            System.out.println("Kilometraza ukupna : " + bus.getCurrentMileage() );
            System.out.println("Kilometraza od servisa: " + mileageSinceLastService);
            System.out.println("Godina proizvodnje : " + yearsOld);
            System.out.println("Dani od servisa : " + timeSinceLastService);
            formula = (double) Math.round(a*yearsOld + b*currentMileage + c*operationalImportance + d*timeSinceLastService + e*mileageSinceLastService + ifMalfunctionProcessed);
            ServiceUrgencyRankings serviceUrgencyRankings = new ServiceUrgencyRankings(bus);
            serviceUrgencyRankings.setScore(formula);
            this.save(serviceUrgencyRankings);
        }
        Integer rankingCounter = 1;
        for(ServiceUrgencyRankings serviceUrgencyRankings : rankingsSorted()){
            serviceUrgencyRankings.setRank(rankingCounter);
            rankingCounter++;
            save(serviceUrgencyRankings);
        }
        // dodace se i operational importance
    }

}
