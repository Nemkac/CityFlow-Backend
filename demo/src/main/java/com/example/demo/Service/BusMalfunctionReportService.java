package com.example.demo.Service;

import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Repository.BusMalfunctionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusMalfunctionReportService {

    @Autowired
    private BusMalfunctionReportRepository busMalfunctionReportRepository;

    public BusMalfunctionReport save(BusMalfunctionReport busMalfunctionReport) { return this.busMalfunctionReportRepository.save(busMalfunctionReport); }
}
