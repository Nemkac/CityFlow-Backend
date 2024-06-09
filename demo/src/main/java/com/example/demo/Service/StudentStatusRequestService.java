package com.example.demo.Service;

import com.example.demo.Model.HealthcareRequest;
import com.example.demo.Model.StudentStatusRequest;
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
public class StudentStatusRequestService {

    @Autowired
    private StudentStatusRepository studentStatusRepository;

    @Transactional
    public StudentStatusRequest store(MultipartFile file, String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date now = new Date();
        StudentStatusRequest studentStatusRequest = new StudentStatusRequest(fileName,username, now, file.getContentType(), file.getBytes());
        return studentStatusRepository.save(studentStatusRequest);
    }
    @Transactional
    public StudentStatusRequest getByNameAndUsername(String name, String username){
        return studentStatusRepository.findByNameAndUsername(name,username);
    }
    @Transactional
    public StudentStatusRequest[] getAllByUsername(String username){
        return studentStatusRepository.findAllByUsername(username);
    }
    public List<String> getAllUsernames(){
        List<StudentStatusRequest> studentStatusRequests = studentStatusRepository.findAll();
        List<String> usernames = new ArrayList<>();
        for (StudentStatusRequest studentStatusRequest : studentStatusRequests) {
            if(!usernames.contains(studentStatusRequest.getUsername())) {
                usernames.add(studentStatusRequest.getUsername());
            }
        }
        return usernames;
    }
}
