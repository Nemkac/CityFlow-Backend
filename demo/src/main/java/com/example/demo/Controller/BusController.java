package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ElectricBusService electricBusService;

    @Autowired
    private ChargingStationService chargingStationService;


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

    @GetMapping(value="/CityFlow/listTest")
    public ResponseEntity<List<ChargingStation>> listTest(){
        return new ResponseEntity<>(this.chargingStationService.getALl(),HttpStatus.OK);
    }

    @GetMapping(value="/CityFlow/databaseFill")
    @ResponseStatus(HttpStatus.OK)
    public void databaseFill(){
        Bus bus1 = new Bus("Gojko1","Kacar1",2002,651,154253,"KG143ZG",386291,4);
        Bus bus2 = new Bus("Gojko2","Kacar2",2019,652,254253,"KG243ZG",38291,11);
        Bus bus3 = new Bus("Gojko3","Kacar3",1999,653,354253,"KG343ZG",1038291,2);
        Bus bus4 = new Bus("Gojko4","Kacar4",2000,654,454253,"KG443ZG",374291,102);
        Bus bus5 = new Bus("Gojko5","Kacar5",2004,655,554253,"KG543ZG",128291,55);
        Bus bus6 = new Bus("Gojko6","Kacar6",2011,656,654253,"KG643ZG",421291,4);
        Bus bus7 = new Bus("Gojko7","Kacar7",2000,657,754253,"KG743ZG",1138291,8);
        Bus bus8 = new Bus("Gojko8","Kacar8",1994,658,854253,"KG843ZG",1448291,14);
        Bus bus9 = new Bus("Gojko9","Kacar9",2021,659,954253,"KG943ZG",37291,28);
        this.busService.save(bus1);
        this.busService.save(bus2);
        this.busService.save(bus3);
        this.busService.save(bus4);
        this.busService.save(bus5);
        this.busService.save(bus6);
        this.busService.save(bus7);
        this.busService.save(bus8);
        this.busService.save(bus9);
        User driver = new User(1,"driver","Vladimir","Blanusa","vb@gmail.com","driver",null,"0643217393","ROLE_DRIVER");
        User serviceAdmin = new User(2,"service","Marko","Stefanovic","ms@gmail.com","service",null,"0643217393","ROLE_SERVICE");
        User chargingAdmin = new User(3,"charger","Stefan","Markovic","sm@gmail.com","charger",null,"0643217393","ROLE_CHARGER");
        User superAdmin = new User(4,"admin","Milos","Milakovic","sm@gmail.com","admin",null,"0643217393","SUPER_ADMIN");
        User noRole = new User(5,"norole","Blagoje","Krecar","sm@gmail.com","norole",null,"0643217393","NO_ROLE");
        this.userService.save(driver);
        this.userService.save(serviceAdmin);
        this.userService.save(chargingAdmin);
        this.userService.save(superAdmin);
        this.userService.save(noRole);
        Driver driveR = new Driver(driver,bus1);
        this.driverService.save(driveR);
        BusMalfunctionReport busMalfunctionReport1 = new BusMalfunctionReport(driveR,bus2,true);
        BusMalfunctionReport busMalfunctionReport2 = new BusMalfunctionReport(driveR,bus5,false);
        BusMalfunctionReport busMalfunctionReport3 = new BusMalfunctionReport(driveR,bus6,true);
        BusMalfunctionReport busMalfunctionReport4 = new BusMalfunctionReport(driveR,bus9,false);
        this.busMalfunctionReportService.save(busMalfunctionReport1);
        this.busMalfunctionReportService.save(busMalfunctionReport2);
        this.busMalfunctionReportService.save(busMalfunctionReport3);
        this.busMalfunctionReportService.save(busMalfunctionReport4);
        ElectricBus elBus1 = new ElectricBus(bus1);
        ElectricBus elBus2 = new ElectricBus(bus2);
        ElectricBus elBus3 = new ElectricBus(bus3);
        ElectricBus elBus4 = new ElectricBus(bus4);
        ElectricBus elBus5 = new ElectricBus(bus5);
        ElectricBus elBus6 = new ElectricBus(bus6);
        ElectricBus elBus7 = new ElectricBus(bus7);
        ElectricBus elBus8 = new ElectricBus(bus8);
        ElectricBus elBus9 = new ElectricBus(bus9);
        List<ElectricBus> elBuses = new ArrayList<ElectricBus>();
        elBuses.add(elBus1);
        elBuses.add(elBus2);
        elBuses.add(elBus3);
        elBuses.add(elBus4);
        elBuses.add(elBus5);
        elBuses.add(elBus6);
        elBuses.add(elBus7);
        elBuses.add(elBus8);
        elBuses.add(elBus9);

        this.electricBusService.save(elBus1);
        this.electricBusService.save(elBus2);
        this.electricBusService.save(elBus3);
        this.electricBusService.save(elBus4);
        this.electricBusService.save(elBus5);
        this.electricBusService.save(elBus6);
        this.electricBusService.save(elBus7);
        this.electricBusService.save(elBus8);
        this.electricBusService.save(elBus9);
        ChargingStation chargingStation1 = new ChargingStation(1200,2,null);
        ChargingStation chargingStation2 = new ChargingStation(1100,2,null);
        ChargingStation chargingStation3 = new ChargingStation(3100,2,null);
        ChargingStation chargingStation4 = new ChargingStation(2695,2,null);
        this.chargingStationService.save(chargingStation1);
        this.chargingStationService.save(chargingStation2);
        this.chargingStationService.save(chargingStation3);
        this.chargingStationService.save(chargingStation4);
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