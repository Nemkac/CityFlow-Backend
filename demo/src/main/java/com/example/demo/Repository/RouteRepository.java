package com.example.demo.Repository;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer> {

    Optional<Route> findById(Integer id);

    Route getByStartingPoint(Location startingPoint);

    Route getByEndPoint(Location endPoint);

    Route save(Route route);

    List<Route> findAll();

    void deleteById(Integer id);

    @Override
    void delete(Route route);
}
