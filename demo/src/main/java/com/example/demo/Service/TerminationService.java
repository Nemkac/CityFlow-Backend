package com.example.demo.Service;

import com.example.demo.Model.Termination;
import com.example.demo.Model.User;
import com.example.demo.Repository.TerminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminationService {
    @Autowired
    private TerminationRepository terminationRepository;

    public Termination save(Termination termination){
        return this.terminationRepository.save(termination);
    }

}
