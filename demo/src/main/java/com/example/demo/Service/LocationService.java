package com.example.demo.Service;

import com.example.demo.Model.Location;
import com.example.demo.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location save(Location location){
        return locationRepository.save(location);
    }

}
