package com.example.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    public void saveStudentFiles(byte[] data, String fileName) throws IOException {

        String uploadDir = "/Users/nemanjatodorovic/Desktop/studentRequests";
        //String uploadDir = "/Users/nemanjaranitovic/Desktop/studentRequests";
        File file = new File(uploadDir + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }
    public void savePensionerFiles(byte[] data, String fileName) throws IOException {
        String uploadDir = "/Users/nemanjatodorovic/Desktop/pensionerRequest";
//        String uploadDir = "/Users/nemanjaranitovic/Desktop/pensionerRequests";
        File file = new File(uploadDir + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }
    public void saveVacationFiles(byte[] data, String fileName) throws IOException {
        String uploadDir = "/Users/nemanjatodorovic/Desktop/vacationRequest";
//        String uploadDir = "/Users/nemanjaranitovic/Desktop/vacationRequests";
        File file = new File(uploadDir + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }
    public void saveHealthcareFiles(byte[] data, String fileName) throws IOException {
        String uploadDir = "/Users/nemanjatodorovic/Desktop/healthcareRequest";
//        String uploadDir = "/Users/nemanjaranitovic/Desktop/healthcareRequests";
        File file = new File(uploadDir + "/" + fileName);
        FileCopyUtils.copy(data, new FileOutputStream(file));
    }

}
