package com.example.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    public void saveStudentFiles(byte[] data, String fileName, String username) throws IOException {
        String uploadDir = "/Users/nemanjaranitovic/Desktop/studentRequests";
        new File(uploadDir+"/"+username).mkdirs();
        System.out.println(uploadDir+"/"+username);
        File file = new File(uploadDir+"/"+username + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }
    public void savePensionerFiles(byte[] data, String fileName,String username) throws IOException {
        String uploadDir = "/Users/nemanjaranitovic/Desktop/pensionerRequest";
        new File(uploadDir+"/"+username).mkdirs();
        File file = new File(uploadDir+"/"+username + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }
    public void saveVacationFiles(byte[] data, String fileName,String username) throws IOException {
        String uploadDir = "/Users/nemanjaranitovic/Desktop/vacationRequest";
        new File(uploadDir+"/"+username).mkdirs();
        File file = new File(uploadDir+"/"+username + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }
    public void saveHealthcareFiles(byte[] data, String fileName,String username) throws IOException {
        String uploadDir = "/Users/nemanjaranitovic/Desktop/healthcareRequest";
        new File(uploadDir+"/"+username).mkdirs();
        File file = new File(uploadDir+"/"+username + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }

}
