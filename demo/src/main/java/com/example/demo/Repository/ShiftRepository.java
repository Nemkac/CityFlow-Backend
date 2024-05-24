package com.example.demo.Repository;

import com.example.demo.Model.Salary;
import com.example.demo.Model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {

    Shift save(Shift shift);
    Shift getById(Integer shiftId);

    List<Shift> findAll();
}
