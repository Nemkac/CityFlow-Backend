package com.example.demo.Controller;

import com.example.demo.Model.Widget;
import com.example.demo.Service.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/widgets")
public class WidgetController {

    @Autowired
    private WidgetService widgetService;
    @GetMapping(path = "/get/all")
    @PreAuthorize("hasAuthority('ROLE_ROUTEADMINISTRATOR')")
    public ResponseEntity<List<Widget>> findAll(@RequestHeader("Authorization") String authorization){
        try {
            List<Widget> widgets = this.widgetService.findAll();
            return new ResponseEntity<>(widgets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
