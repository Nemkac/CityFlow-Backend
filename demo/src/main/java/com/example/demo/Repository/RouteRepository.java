package com.example.demo.Repository;

import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    Route getById(int id);

    Route getByStartingPoint(Location startingPoint);

    Route getByEndPoint(Location endPoint);

    Route save(Route route);

    List<Route> findAll();

    @Override
    void delete(Route route);
}
