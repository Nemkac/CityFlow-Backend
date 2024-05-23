package com.example.demo.Service;

import com.example.demo.Model.PensionerStatusRequest;
import com.example.demo.Model.StudentStatusRequest;
import com.example.demo.Repository.PensionerStatusRepository;
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
public class PensionerStatusRequestService {

    @Autowired
    private PensionerStatusRepository pensionerStatusRepository;

    @Transactional
    public PensionerStatusRequest store(MultipartFile file, String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date now = new Date();
        PensionerStatusRequest pensionerStatusRequest = new PensionerStatusRequest(fileName,username, now, file.getContentType(), file.getBytes());
        return pensionerStatusRepository.save(pensionerStatusRequest);
    }
    @Transactional
    public PensionerStatusRequest getByNameAndUsername(String name, String username){
        return pensionerStatusRepository.findByNameAndUsername(name,username);
    }
    @Transactional
    public PensionerStatusRequest[] getAllByUsername(String username){
        return pensionerStatusRepository.findAllByUsername(username);
    }
    public List<String> getAllUsernames(){
        List<PensionerStatusRequest> pensionerStatusRequests = pensionerStatusRepository.findAll();
        List<String> usernames = new ArrayList<>();
        for (PensionerStatusRequest pensionerStatusRequest : pensionerStatusRequests) {
            if(!usernames.contains(pensionerStatusRequest.getUsername())) {
                usernames.add(pensionerStatusRequest.getUsername());
            }
        }
        return usernames;
    }
}
