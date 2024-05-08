package com.example.demo.Service;

import com.example.demo.Model.Location;
import com.example.demo.Model.Route;
import com.example.demo.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitialDataInsertionService {

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private LocationService locationService;

    @Transactional
    public void insertInitialData() {

//        LocalDate user1BirthDate = LocalDate.ofEpochDay(2002-26-1);
//        User user1 = new User(
//                "Blanushaolin",
//                "Vladimir",
//                "Blanusa",
//                "vblanusa@gmail.com",
//                "vb123",
//                user1BirthDate,
//                "0658291222",
//                "ROLE_USER"
//        );

//        LocalDate user2BirthDate = LocalDate.ofEpochDay(2001-15-7);
//        User user2 = new User(
//                "Ranita",
//                "Nemanja",
//                "Ranitovic",
//                "nranitovic@gmail.com",
//                "nr123",
//                user2BirthDate,
//                "0644819288",
//                "ROLE_USER"
//        );

        LocalDate user3BirthDate = LocalDate.ofEpochDay(2002-25-1);
        User user3 = new User(
                "Lele",
                "Andrea",
                "Mitic",
                "amitic@gmail.com",
                "am123",
                user3BirthDate,
                "0672182233",
                "ROLE_HRAdministrator",
                true
        );

        LocalDate user4BirthDate = LocalDate.ofEpochDay(2002-18-1);
        User user4 = new User(
                "Nemkac",
                "Nemanja",
                "Todorovic",
                "nemanjatodorovic132002002@gmail.com",
                "nt123",
                user4BirthDate,
                "0644316167",
                "ROLE_ROUTEADMINISTRATOR",
                true
        );

//        userService.addUser(user1);
//        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);

        Location location1 = new Location(45.242006, 19.842685);
        Location location2 = new Location(45.241652, 19.842843);
        Location location3 = new Location(45.243805, 19.841589);
        Location location4 = new Location(45.246582, 19.840096);
        Location location5 = new Location(45.247929, 19.839362);

        locationService.save(location1);
        locationService.save(location2);
        locationService.save(location3);
        locationService.save(location4);
        locationService.save(location5);

        List<Location> stations1 = new ArrayList<>();
        stations1.add(location2);
        stations1.add(location3);
        stations1.add(location4);

        Route route1 = new Route("7A", location1, stations1, location5, "6:30", "00:30");
        routeService.save(route1);
    }
}
