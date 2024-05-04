package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.BusMalfunctionReport;
import com.example.demo.Model.ServiceUrgencyRankings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceUrgencyRankingsRepository extends JpaRepository<ServiceUrgencyRankings,Integer> {

    ServiceUrgencyRankings save(ServiceUrgencyRankings serviceUrgencyRankings);
    List<ServiceUrgencyRankings> findAll();
    ServiceUrgencyRankings getServiceUrgencyRankingsById(Integer id);

    ServiceUrgencyRankings getServiceUrgencyRankingsByBus(Bus bus);
    List<ServiceUrgencyRankings> findAllByOrderByScoreDesc();
    List<ServiceUrgencyRankings> findAllByOrderByRankAsc();

    void deleteAll();
}
