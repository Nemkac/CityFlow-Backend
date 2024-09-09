package com.example.demo.Repository;

import com.example.demo.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location save(Location location);
    Location getByLatitudeAndLongitude(double Latitude, double Longitude);
    Optional<Location> findById(Integer id);
    List<Location> findAll();
}
