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

    public List<ServiceUrgencyRankings> rankingsSorted() { return this.rankingsSorted(); }

    // ovo ce da se poziva svaki put kada se u bazu ubaci novi bus, svaki put se normalizuju svi parametri i iznova racunaju rankovi
    public void CreateRankings(){
        List<Bus> buses = busService.getAll();
        List<BusServicing> busServicings = busServicingService.getAll();
        Integer yearsOld = 0;
        Integer currentMileage;
        Integer operationalImportance = 0;
        Long timeSinceLastService = 0L;
        Integer ifMalfunctionProcessed = 0;
        Double formula = 0.0;
        double a = 0.1;
        double b = 0.25;
        double c = 0.2;
        double d = 0.15;
        double e = 0.3;
        for(Bus bus : buses){
            Duration duration = Duration.between(LocalDate.now(),busServicingService.getById(bus.getBusId()).getDate());
            timeSinceLastService = duration.toDays();
            yearsOld = LocalDate.now().getYear() - bus.getModelYear();
            currentMileage = bus.getCurrentMileage();
            // operationalImportance = bus ce imati vezanu rutu za njega, iz nje vuces operativnu vaznost
            ifMalfunctionProcessed = busMalfunctionReportService.getAllByBusSortByDate(bus).get(0).getIfProcessed() ? 1 : 0;
            formula = a*yearsOld + b*currentMileage + c*operationalImportance + d*timeSinceLastService + e*ifMalfunctionProcessed;
            ServiceUrgencyRankings serviceUrgencyRankings = this.getByBus(bus);
            serviceUrgencyRankings.setScore(formula);
        }
        Integer rankingCounter = 1;
        for(ServiceUrgencyRankings serviceUrgencyRankings : rankingsSorted()){
            serviceUrgencyRankings.setRank(rankingCounter);
            rankingCounter++;
            save(serviceUrgencyRankings);
        }
        // SVE OVO TESTIRAJ, POCEVSI OD ONOG SORTIRANOG NIZA SKOROVA
    }

}
