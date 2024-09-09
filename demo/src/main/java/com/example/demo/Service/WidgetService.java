package com.example.demo.Service;

import com.example.demo.Exceptions.RouteNotFoundException;
import com.example.demo.Exceptions.WidgetNotFoundException;
import com.example.demo.Model.RouteAdministrator;
import com.example.demo.Model.Widget;
import com.example.demo.Repository.WidgetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WidgetService {
    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private RouteAdministratorService routeAdministratorService;

    public List<Widget> findAll() {
        return this.widgetRepository.findAll();
    }

    public Widget findById(Integer id) {
        Optional<Widget> widget =  widgetRepository.findById(id);
        return widget.orElseThrow(() -> new WidgetNotFoundException("Widget could not be found by given ID"));
    }
}
