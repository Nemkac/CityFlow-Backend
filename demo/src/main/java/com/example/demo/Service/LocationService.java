package com.example.demo.Service;

import com.example.demo.Exceptions.LocationNotFoundException;
import com.example.demo.Exceptions.RouteNotFoundException;
import com.example.demo.Model.Bus;
import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Model.Station;
import com.example.demo.Repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location save(Location location){
        Location savedLocation = locationRepository.save(location);
        Station station = new Station(savedLocation);
        return savedLocation;
    }

    public Location getByLatitudeAndLongitude(double latitude, double longitude){
        return locationRepository.getByLatitudeAndLongitude(latitude, longitude);
    }

    public List<Location> getAll(){
        return locationRepository.findAll();
    }

    public Location findById(Integer id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        return optionalLocation.orElseThrow(() -> new LocationNotFoundException("Location could not be found by given ID"));
    }

}
