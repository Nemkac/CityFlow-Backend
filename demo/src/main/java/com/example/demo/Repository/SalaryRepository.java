package com.example.demo.Repository;

import com.example.demo.Model.Salary;
import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    Salary save(Salary salary);

    void delete(Salary salary);
    Salary findByUser(User user);
}

