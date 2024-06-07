package com.example.demo.Service;

import com.example.demo.Model.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InitialDataInsertionService {

    @Autowired
    private UserService userService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CardService cardService;
    @Autowired
    private BusService busService;
    @Autowired
    private FinancialReportService financialReportService;

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

        LocalDate user6BirthDate = LocalDate.of(1985, 3, 15);
        User user6 = new User(
                "DriverMilan",
                "Milan",
                "Petrović",
                "milan.petrović@gmail.com",
                "mp123",
                user6BirthDate,
                "0623456789",
                "ROLE_DRIVER",
                true
        );
        userService.addUser(user6);

        LocalDate user7BirthDate = LocalDate.of(1978, 8, 30);
        User user7 = new User(
                "DriverMarko",
                "Marko",
                "Nikolić",
                "marko.nikolic@gmail.com",
                "mn123",
                user7BirthDate,
                "0634567890",
                "ROLE_DRIVER",
                true
        );

        userService.addUser(user7);
        Card card1 = new Card(
                "1234 5678",
                "04/05",
                "Nemanja Ranitovic",
                "000",
                123123123
        );
        Card card2 = new Card(
                "4321 5678",
                "05/06",
                "Nemanja Todorovic",
                "000",
                123123123
        );
        LocalDate user5BirthDate = LocalDate.ofEpochDay(2002-18-1);
        User user5 = new User(
                "Djuro",
                "Djordje",
                "Djordjevic",
                "dj123@gmail.com",
                "dj123",
                user5BirthDate,
                "0645678910",
                "ROLE_KYCADMINISTRATOR"
        );


        cardService.addCard(card1);
        cardService.addCard(card2);
//        userService.addUser(user1);
//        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);

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

        List<Route> routesForBus1 = new ArrayList<Route>();
        routesForBus1.add(route1);

        Bus bus1 = new Bus("SM 076 AT",routesForBus1);

        busService.save(bus1);


        FinancialReport report1 = new FinancialReport(1, 1000, 30000, 360000, 5000, 20000, 3000, 4000, new Date(), 5000);
        FinancialReport report2 = new FinancialReport(2, 1200, 32000, 384000, 6000, 21000, 3200, 4200, new Date(), 5200);
        FinancialReport report3 = new FinancialReport(3, 1100, 31000, 372000, 5500, 20500, 3100, 4100, new Date(), 5100);
        FinancialReport report4 = new FinancialReport(4, 1300, 33000, 396000, 6500, 22000, 3300, 4300, new Date(), 5300);
        FinancialReport report5 = new FinancialReport(5, 1400, 34000, 408000, 7000, 23000, 3400, 4400, new Date(), 5400);

        financialReportService.save(report1);
        financialReportService.save(report2);
        financialReportService.save(report3);
        financialReportService.save(report4);
        financialReportService.save(report5);
    }
}
