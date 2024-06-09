package com.example.demo.Service;

import com.example.demo.Model.HealthcareRequest;
import com.example.demo.Model.StudentStatusRequest;
import com.example.demo.Model.VacationRequest;
import com.example.demo.Repository.HealthcareRepository;
import com.example.demo.Repository.StudentStatusRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HealthcareService {

    @Autowired
    private HealthcareRepository healthcareRepository;

    @Transactional
    public HealthcareRequest store(MultipartFile file, String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date now = new Date();
        HealthcareRequest healthcareRequest = new HealthcareRequest(fileName,username, now, file.getContentType(), file.getBytes());
        return healthcareRepository.save(healthcareRequest);
    }
    @Transactional
    public HealthcareRequest getByNameAndUsername(String name, String username){
        return healthcareRepository.findByNameAndUsername(name,username);
    }
    @Transactional
    public HealthcareRequest[] getAllByUsername(String username){
        return healthcareRepository.findAllByUsername(username);
    }
    public List<String> getAllUsernames(){
        List<HealthcareRequest> healthcareRequests = healthcareRepository.findAll();
        List<String> usernames = new ArrayList<>();
        for (HealthcareRequest healthcareRequest : healthcareRequests) {
            if(!usernames.contains(healthcareRequest.getUsername())) {
                usernames.add(healthcareRequest.getUsername());
            }
        }
        return usernames;
    }
}
