package com.example.demo.Service;

import com.example.demo.Model.Shift;
import com.example.demo.Repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    public Shift save(Shift shift){
        return this.shiftRepository.save(shift);
    }
    public Shift getById(Integer shiftId){
        return shiftRepository.getById(shiftId);
    }

    public List<Shift> findAll(){
        return shiftRepository.findAll();
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public List<Shift> saveAll(List<Shift> shifts){
        return shiftRepository.saveAll(shifts); // Batch save method
    }
}
