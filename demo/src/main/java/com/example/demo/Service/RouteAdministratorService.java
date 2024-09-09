package com.example.demo.Service;

import com.example.demo.Model.RouteAdministrator;
import com.example.demo.Model.User;
import com.example.demo.Model.Widget;
import com.example.demo.Repository.RouteAdministratorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteAdministratorService {

    @Autowired
    private RouteAdministratorRepository routeAdministratorRepository;
    public RouteAdministrator getByUser(User user){
        return this.routeAdministratorRepository.findByUser(user);
    }

    public RouteAdministrator save(RouteAdministrator admin) {
        return this.routeAdministratorRepository.save(admin);
    }

    public RouteAdministrator findById(Integer routeAdminId) {
        return this.routeAdministratorRepository.findByRouteAdminId(routeAdminId);
    }

    public List<Widget> getWidgets(Integer routeAdminId) {
        RouteAdministrator admin = this.findById(routeAdminId);
        return admin.getWidgets();
    }

    public void removeWidgetFromDashboard(Widget selectedWidget, Integer routeAdminId) {
        RouteAdministrator admin = this.findById(routeAdminId);
        List<Widget> adminWidgets = admin.getWidgets();

        adminWidgets.removeIf(widget -> widget.getId().equals(selectedWidget.getId()));
        this.save(admin);
    }

    @Transactional
    public void addWidgetsToDashboard(List<Widget> widgets, Integer routeAdminId) {
        RouteAdministrator admin = this.findById(routeAdminId);
        List<Widget> selectedWidgets = new ArrayList<>(widgets);

        for(Widget widget : selectedWidgets){
            if(!admin.getWidgets().contains(widget)){
                admin.getWidgets().add(widget);
            }
        }
        this.save(admin);
    }
}
