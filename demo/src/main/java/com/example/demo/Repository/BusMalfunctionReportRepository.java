package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusMalfunctionReportRepository extends JpaRepository<BusMalfunctionReport,Integer> {

    BusMalfunctionReport save(BusMalfunctionReport busMalfunctionReport);

    BusMalfunctionReport getByBusMalfunctionReportId(Integer id);

    List<BusMalfunctionReport> getByIfProcessed(boolean ifProcessed);

    List<BusMalfunctionReport> getByDriver(Driver driver);
    List<BusMalfunctionReport> getByBus(Bus bus);
}
