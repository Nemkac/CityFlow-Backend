package com.example.demo.Repository;

import com.example.demo.Model.RouteAdministrator;
import com.example.demo.Model.User;
import com.example.demo.Model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteAdministratorRepository extends JpaRepository<RouteAdministrator, Integer> {
    RouteAdministrator save(RouteAdministrator routeAdministrator);
    RouteAdministrator findByUser(User user);

    RouteAdministrator findByRouteAdminId(Integer id);
}
