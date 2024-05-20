package com.example.demo.Service;
import com.example.demo.Model.Salary;
import com.example.demo.Model.User;
import com.example.demo.Repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    public Salary save(Salary salary){
        return this.salaryRepository.save(salary);
    }
    public void delete(Salary salary) {
        salaryRepository.delete(salary);
    }
    public Salary getByUser(User user) {
        return salaryRepository.findByUser(user);
    }
}

