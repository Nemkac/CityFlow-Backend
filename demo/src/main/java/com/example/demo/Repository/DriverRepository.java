package com.example.demo.Repository;

import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver save(Driver driver);

    void delete(Driver driver);
    Driver findByUser(User user);
}
