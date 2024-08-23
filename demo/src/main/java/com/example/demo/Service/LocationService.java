package com.example.demo.Service;

import com.example.demo.Model.Bus;
import com.example.demo.Model.Location;
import com.example.demo.Model.Station;
import com.example.demo.Repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;


    public Location save(Location location){
        Location savedLocation = locationRepository.save(location);
        Station station = new Station(savedLocation);
        //sendLocationToGraphDatabase(savedLocation, station);
        return savedLocation;
    }



    public Location getByLatitudeAndLongiture(double latitude, double longitude){
        return locationRepository.getByLatitudeAndLongitude(latitude, longitude);
    }

}
