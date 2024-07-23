package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.Driver;
import com.example.demo.Repository.BusMalfunctionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusMalfunctionReportService {

    @Autowired
    private BusMalfunctionReportRepository busMalfunctionReportRepository;

    public BusMalfunctionReport save(BusMalfunctionReport busMalfunctionReport) { return this.busMalfunctionReportRepository.save(busMalfunctionReport); }
    public List<BusMalfunctionReport> findAll() { return this.busMalfunctionReportRepository.findAll();}
    public BusMalfunctionReport getById(Integer id) { return this.busMalfunctionReportRepository.getByBusMalfunctionReportId(id); }
    public List<BusMalfunctionReport> getAllByDriver(Driver driver) { return this.busMalfunctionReportRepository.getByDriver(driver); }
    public List<BusMalfunctionReport> getAllByBus(Bus bus) { return this.busMalfunctionReportRepository.getByBus(bus); }
    public List<BusMalfunctionReport> getAllProcessed() { return this.busMalfunctionReportRepository.getByIfProcessed(true); }
    public List<BusMalfunctionReport> getAllUnprocessed() { return this.busMalfunctionReportRepository.getByIfProcessed(false); }
    public List<BusMalfunctionReport> getAllByBusSortByDate(Bus bus) { return this.busMalfunctionReportRepository.getBusMalfunctionReportByBusOrderByDateDesc(bus); }

}
