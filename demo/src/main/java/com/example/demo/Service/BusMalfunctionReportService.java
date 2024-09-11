package com.example.demo.Service;

import com.example.demo.DTO.MalfunctionDataDTO;
import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.Driver;
import com.example.demo.Repository.BusMalfunctionReportRepository;
import com.example.demo.Repository.MalfunctionDataDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusMalfunctionReportService {

    @Autowired
    private BusMalfunctionReportRepository busMalfunctionReportRepository;

    @Autowired
    private BusService busService;

    @Autowired
    private MalfunctionDataDTORepository malfunctionDataDTORepository;


    public BusMalfunctionReport save(BusMalfunctionReport busMalfunctionReport) { return this.busMalfunctionReportRepository.save(busMalfunctionReport); }
    public List<BusMalfunctionReport> findAll() { return this.busMalfunctionReportRepository.findAll();}
    public BusMalfunctionReport getById(Integer id) { return this.busMalfunctionReportRepository.getByBusMalfunctionReportId(id); }
    public List<BusMalfunctionReport> getAllByDriver(Driver driver) { return this.busMalfunctionReportRepository.getByDriver(driver); }
    public List<BusMalfunctionReport> getAllByBus(Bus bus) { return this.busMalfunctionReportRepository.getByBus(bus); }
    public List<BusMalfunctionReport> getAllProcessed() { return this.busMalfunctionReportRepository.getByIfProcessed(true); }
    public List<BusMalfunctionReport> getAllUnprocessed() { return this.busMalfunctionReportRepository.getByIfProcessed(false); }
    public List<BusMalfunctionReport> getAllByBusSortByDate(Bus bus) { return this.busMalfunctionReportRepository.getBusMalfunctionReportByBusOrderByDateDesc(bus); }
    public List<MalfunctionDataDTO> findAllMalfunctionData() {return this.malfunctionDataDTORepository.findAll(); }
    public List<MalfunctionDataDTO> findAllByOrderByEver() {return this.malfunctionDataDTORepository.findAllByOrderByEverDesc(); }

    public MalfunctionDataDTO getDataForManufacturer(String manufacturer) {
        int inLastMonth = 0;
        int inLastYear = 0;
        int inLastFiveYears = 0;
        int ever = 0;
        List<BusMalfunctionReport> allReports = this.findAll();
        List<BusMalfunctionReport> foundReports = new ArrayList<>();
        for(BusMalfunctionReport report : allReports) {
            if(report.getBus().getManufacturer().equals(manufacturer)) {
                foundReports.add(report);
            }
        }
        if(foundReports.size() == 0) {
            return new MalfunctionDataDTO(manufacturer,0,0,0,0);
        }
        for(BusMalfunctionReport report:foundReports) {
            if(report.getDate().isAfter(LocalDate.now().minusDays(30))){
                inLastMonth += 1;
                inLastYear += 1;
                inLastFiveYears +=1;
                ever += 1;
            } else if (report.getDate().isAfter(LocalDate.now().minusYears(1))){
                inLastYear += 1;
                inLastFiveYears +=1;
                ever += 1;
            } else if (report.getDate().isAfter(LocalDate.now().minusYears(5))){
                inLastFiveYears += 1;
                ever += 1;
            } else if (report.getDate().isAfter(LocalDate.now().minusYears(100))){
                ever += 1;
            }
        }
        return new MalfunctionDataDTO(manufacturer,inLastMonth,inLastYear,inLastFiveYears,ever);
    }

    public boolean ifManufactuerInList(String manufacturer, List<String> manufacturers) {
        if(manufacturers.isEmpty()){
            return false;
        }
        for(String manufacturerInList : manufacturers){
            if(manufacturer.equals(manufacturerInList)) {
                return true;
            }
        }
        return false;
    }

    public void fillMalfunctionData(){
        this.malfunctionDataDTORepository.deleteAll();
        List<Bus> buses = this.busService.getAll();
        List<String> manufacturers = new ArrayList<>();
        for(Bus bus : buses) {
            if(!ifManufactuerInList(bus.getManufacturer(),manufacturers)){
                manufacturers.add(bus.getManufacturer());
            }
        }
        for(String manufacturer : manufacturers){
            this.malfunctionDataDTORepository.save(this.getDataForManufacturer(manufacturer));
        }
    }


}
