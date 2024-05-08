package com.example.demo.Repository;

import com.example.demo.Model.Accountant;
import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountantRepository extends JpaRepository<Accountant, Integer> {
    Accountant save(Accountant accountant);
    Accountant findByUser(User user);


}
