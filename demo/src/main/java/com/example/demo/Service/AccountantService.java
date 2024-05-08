package com.example.demo.Service;

import com.example.demo.Model.Accountant;
import com.example.demo.Model.Driver;
import com.example.demo.Model.User;
import com.example.demo.Repository.AccountantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountantService {
    @Autowired
    private AccountantRepository accountantRepository;
    public Accountant save(Accountant accountant){
        return this.accountantRepository.save(accountant);
    }
    public Accountant getByUser(User user) {
        return accountantRepository.findByUser(user);
    }


}
