package com.example.demo.Repository;

import com.example.demo.Model.BusMalfunctionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusMalfunctionReportRepository extends JpaRepository<BusMalfunctionReport,Integer> {

    BusMalfunctionReport save(BusMalfunctionReport busMalfunctionReport);

}
