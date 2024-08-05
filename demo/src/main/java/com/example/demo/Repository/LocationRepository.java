package com.example.demo.Repository;

import com.example.demo.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location save(Location location);

    Location getByLatitudeAndLongitude(double Latitude, double Longitude);

    Location getById(int id);

    List<Location> findAll();
}
