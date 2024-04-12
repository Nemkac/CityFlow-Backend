package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class BusController {
    @Autowired
    private BusService busService;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private BusMalfunctionReportService busMalfunctionReportService;

    @Autowired
    private BusServicingService busServicingService;



    // za testiranje, brise se posle
    @GetMapping(value = "/CityFlow/testBus")
    public ResponseEntity<Bus> testBus() {
        Bus bus = new Bus();
        bus.setManufacturer("Mann");
        bus.setModel("CFXD");
        bus.setModelYear(2019);
        bus.setSeatingCapacity(69);
        bus.setChassisNumber(213948323);
        bus.setRegistrationNumber("KG265GT");
        bus.setCurrentMileage(12941);
        busService.save(bus);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", value="/CityFlow/addBus")
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        if(busService.ifExists(bus.getChassisNumber()) || busService.save(bus) == null){
            return new ResponseEntity("Bus already exists",HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Bus>(bus,HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/databaseFill")
    @ResponseStatus(HttpStatus.OK)
    public void sortRankings(){
        Bus bus1 = new Bus("Gojko1","Kacar1",2002,651,154253,"KG143ZG",386291);
        Bus bus2 = new Bus("Gojko2","Kacar2",2019,652,254253,"KG243ZG",38291);
        Bus bus3 = new Bus("Gojko3","Kacar3",1999,653,354253,"KG343ZG",1038291);
        Bus bus4 = new Bus("Gojko4","Kacar4",2000,654,454253,"KG443ZG",374291);
        Bus bus5 = new Bus("Gojko5","Kacar5",2004,655,554253,"KG543ZG",128291);
        Bus bus6 = new Bus("Gojko6","Kacar6",2011,656,654253,"KG643ZG",421291);
        Bus bus7 = new Bus("Gojko7","Kacar7",2000,657,754253,"KG743ZG",1138291);
        Bus bus8 = new Bus("Gojko8","Kacar8",1994,658,854253,"KG843ZG",1448291);
        Bus bus9 = new Bus("Gojko9","Kacar9",2021,659,954253,"KG943ZG",37291);
        this.busService.save(bus1);
        this.busService.save(bus2);
        this.busService.save(bus3);
        this.busService.save(bus4);
        this.busService.save(bus5);
        this.busService.save(bus6);
        this.busService.save(bus7);
        this.busService.save(bus8);
        this.busService.save(bus9);
        User user = new User(1,"","","","vb","vb",null,"","ROLE_DRIVER");
        this.userService.save(user);
        Driver driver = new Driver(user);
        this.driverService.save(driver);
        BusMalfunctionReport busMalfunctionReport1 = new BusMalfunctionReport(driver,bus2,true);
        BusMalfunctionReport busMalfunctionReport2 = new BusMalfunctionReport(driver,bus5,false);
        BusMalfunctionReport busMalfunctionReport3 = new BusMalfunctionReport(driver,bus6,true);
        BusMalfunctionReport busMalfunctionReport4 = new BusMalfunctionReport(driver,bus9,false);
        this.busMalfunctionReportService.save(busMalfunctionReport1);
        this.busMalfunctionReportService.save(busMalfunctionReport2);
        this.busMalfunctionReportService.save(busMalfunctionReport3);
        this.busMalfunctionReportService.save(busMalfunctionReport4);
        LocalDate date1 = LocalDate.now().minusMonths(12);
        LocalDate date2 = LocalDate.now().minusMonths(15);
        LocalDate date3 = LocalDate.now().minusMonths(12);
        BusServicing busServicing1 = new BusServicing(bus1,124391,date1,true,"ok");
        BusServicing busServicing2 = new BusServicing(bus1,124391,date2,true,"ok");
        BusServicing busServicing3 = new BusServicing(bus2,24391,date3,true,"ok");
        this.busServicingService.save(busServicing1);
        this.busServicingService.save(busServicing2);
        this.busServicingService.save(busServicing3);
    }


}