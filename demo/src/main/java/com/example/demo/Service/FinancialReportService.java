package com.example.demo.Service;

import com.example.demo.Model.FinancialReport;
import com.example.demo.Model.Salary;
import com.example.demo.Repository.FinancialReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialReportService {

    @Autowired
    private FinancialReportRepository financialReportRepository;

    public FinancialReport save(FinancialReport financialReport){
        return this.financialReportRepository.save(financialReport);
    }

    public List<FinancialReport> getAllReports() {
        return financialReportRepository.findAll();
    }

}