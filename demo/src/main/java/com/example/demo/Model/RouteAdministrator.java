package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "RouteAdministrator")
public class RouteAdministrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer routeAdminId;

    @OneToOne
    private User user;

    @ManyToMany
    @JoinColumn(name = "widgetId")
    private List<Widget> widgets;

    public RouteAdministrator() {
    }

    public RouteAdministrator(User user, List<Widget> widgets) {
        this.user = user;
        this.widgets = widgets;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }

    public Integer getRouteAdminId() {
        return routeAdminId;
    }

    public void setRouteAdminId(Integer routeAdminId) {
        this.routeAdminId = routeAdminId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
