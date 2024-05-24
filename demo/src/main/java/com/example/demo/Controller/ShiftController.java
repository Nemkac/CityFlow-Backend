package com.example.demo.Controller;

import com.example.demo.DTO.ShiftDTO;
import com.example.demo.Model.Route;
import com.example.demo.Model.Shift;
import com.example.demo.Model.User;
import com.example.demo.Service.RouteService;
import com.example.demo.Service.ShiftService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/CityFlow/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private UserService userService;

    @Autowired
    private RouteService routeService;

    @GetMapping("/all")
    public ResponseEntity<List<ShiftDTO>> getAllShifts() {
        List<Shift> shifts = shiftService.getAllShifts();
        List<ShiftDTO> dtos = new ArrayList<>();
        for(Shift shift: shifts)
        {
            Route route = shift.getRoute();
            ShiftDTO dto = new ShiftDTO(
                    shift.getId(),
                    route != null ? route.getId() : null,
                    shift.getStartTime(),
                    shift.getEndTime(),
                    shift.getLocation()
            );
            dtos.add(dto);

        }
        if (shifts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addShift(@RequestBody ShiftDTO shiftDTO) {
        User user = userService.findById(shiftDTO.getUserId());
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        System.out.println("ShiftDTO Route ID: " + shiftDTO.getRouteId());

        Shift shift = new Shift();
        shift.setUser(user);
        shift.setStartTime(shiftDTO.getStartTime());
        shift.setEndTime(shiftDTO.getEndTime());
        shift.setLocation(shiftDTO.getLocation());

        if ("ROLE_DRIVER".equals(user.getRoles())) {
            Route route = routeService.getById(shiftDTO.getRouteId());
            System.out.println("Found Route: " + (route != null ? route.getId() : "null"));
            if (route == null) {
                return new ResponseEntity<>("Route not found", HttpStatus.BAD_REQUEST);
            }
            shift.setRoute(route);
        } else {
            shift.setRoute(null);
        }

        try {
            shiftService.save(shift);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
