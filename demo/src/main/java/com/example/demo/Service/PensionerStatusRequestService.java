package com.example.demo.Service;

import com.example.demo.Model.PensionerStatusRequest;
import com.example.demo.Model.StudentStatusRequest;
import com.example.demo.Repository.PensionerStatusRepository;
import com.example.demo.Repository.StudentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class PensionerStatusRequestService {

    @Autowired
    private PensionerStatusRepository pensionerStatusRepository;

    public PensionerStatusRequest store(MultipartFile file, String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date now = new Date();
        PensionerStatusRequest pensionerStatusRequest = new PensionerStatusRequest(fileName,username, now, file.getContentType(), file.getBytes());
        return pensionerStatusRepository.save(pensionerStatusRequest);
    }
    public PensionerStatusRequest getByNameAndUsername(String name, String username){
        return pensionerStatusRepository.findByNameAndUsername(name,username);
    }
}
