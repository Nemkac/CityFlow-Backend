package com.example.demo.Service;

import com.example.demo.Model.PensionerStatusRequest;
import com.example.demo.Model.VacationRequest;
import com.example.demo.Repository.PensionerStatusRepository;
import com.example.demo.Repository.VacationRepository;
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
public class VacationService {

    @Autowired
    private VacationRepository vacationRepository;

    @Transactional
    public VacationRequest store(MultipartFile file, String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date now = new Date();
        VacationRequest vacationRequest = new VacationRequest(fileName,username, now, file.getContentType(), file.getBytes());
        return vacationRepository.save(vacationRequest);
    }
    @Transactional
    public VacationRequest getByNameAndUsername(String name, String username){
        return vacationRepository.findByNameAndUsername(name,username);
    }
    @Transactional
    public VacationRequest[] getAllByUsername(String username){
        return vacationRepository.findAllByUsername(username);
    }

    public List<String> getAllUsernames(){
        List<VacationRequest> vacationRequests = vacationRepository.findAll();
        List<String> usernames = new ArrayList<>();
        for (VacationRequest vacationRequest : vacationRequests) {
            if(!usernames.contains(vacationRequest.getUsername())) {
                usernames.add(vacationRequest.getUsername());
            }
        }
        return usernames;
    }


}
