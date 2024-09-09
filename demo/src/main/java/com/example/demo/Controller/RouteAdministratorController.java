package com.example.demo.Controller;

import com.example.demo.Model.RouteAdministrator;
import com.example.demo.Model.User;
import com.example.demo.Model.Widget;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.RouteAdministratorService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/routeAdministrator")
public class RouteAdministratorController {

    @Autowired
    private RouteAdministratorService routeAdministratorService;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @GetMapping(path = "/get/byUser")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<RouteAdministrator> getRouteAdministrator (@RequestHeader("Authorization") String authorization){
        try {
            String bearerToken = authorization.substring(7);
            String username = jwtService.extractUsername(bearerToken);
            User user = userService.FindByUsername(username);

            RouteAdministrator admin = routeAdministratorService.getByUser(user);

            return new ResponseEntity<>(admin, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/widget/remove")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<String> removeWidgetFromDashboard(@RequestHeader("Authorization") String authorization, @RequestBody Widget selectedWidget){
        try {
            String bearerToken = authorization.substring(7);
            String username = jwtService.extractUsername(bearerToken);
            User user = userService.FindByUsername(username);
            RouteAdministrator admin = routeAdministratorService.getByUser(user);

            this.routeAdministratorService.removeWidgetFromDashboard(selectedWidget, admin.getRouteAdminId());

            return new ResponseEntity<>("Widget successfully deleted from dashboard!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error with accessing widget deletion logics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/widget/add")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<String> addWidgetToDashboard(@RequestHeader("Authorization") String authorization, @RequestBody List<Widget> selectedWidgets){
        try {
            String bearerToken = authorization.substring(7);
            String username = jwtService.extractUsername(bearerToken);
            User user = userService.FindByUsername(username);
            RouteAdministrator admin = routeAdministratorService.getByUser(user);

            this.routeAdministratorService.addWidgetsToDashboard(selectedWidgets, admin.getRouteAdminId());

            return new ResponseEntity<>("Widget successfully added!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Error with accessing widget addition logics", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/widget/get/all")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<List<Widget>> getAdminWidgets(@RequestHeader("Authorization") String authorization){
        try {
            String bearerToken = authorization.substring(7);
            String username = jwtService.extractUsername(bearerToken);
            User user = userService.FindByUsername(username);
            RouteAdministrator admin = routeAdministratorService.getByUser(user);

            List<Widget> adminWidgets = admin.getWidgets();

            return new ResponseEntity<>(adminWidgets, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
