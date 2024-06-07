package com.example.demo.Repository;

import com.example.demo.Model.FinancialReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialReportRepository extends JpaRepository<FinancialReport, Integer> {
    List<FinancialReport> findAll();
   /* void saveAll(List<FinancialReport> reports);*/

    FinancialReport save (FinancialReport financialReport);
}
