package com.example.demo.Controller;


import com.example.demo.DTO.SingleStringDTO;
import com.example.demo.Model.StudentStatusRequest;
import com.example.demo.Service.PensionerStatusRequestService;
import com.example.demo.Service.StudentStatusRequestService;
import com.example.demo.Service.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class DocumentController {
    @Autowired
    public StudentStatusRequestService studentStatusRequestService;
    @Autowired
    public PensionerStatusRequestService pensionerStatusRequestService;
    @Autowired
    public JwtService jwtService;

    //403 ako se ne posalju svi fajlovi
    @PostMapping(path = "/document/studentRequestUpload")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> uploadFile(@RequestHeader("Authorization") String authorization,
                                             @RequestParam("zahtev") MultipartFile zahtev,
                                             @RequestParam("obrada") MultipartFile obrada,
                                             @RequestParam("fotografija") MultipartFile fotografija,
                                             @RequestParam("indeks") MultipartFile indeks
    ) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);

        studentStatusRequestService.store(zahtev,username);
        String message = "Uploaded the file successfully: " + zahtev.getOriginalFilename() + '\n';
        studentStatusRequestService.store(obrada,username);
        message += "Uploaded the file successfully: " + obrada.getOriginalFilename() + '\n';
        studentStatusRequestService.store(fotografija,username);
        message += "Uploaded the file successfully: " + fotografija.getOriginalFilename() + '\n';
        studentStatusRequestService.store(indeks,username);
        message += "Uploaded the file successfully: " + indeks.getOriginalFilename() + '\n';
        return ResponseEntity.status(200).body(message);
    }
    @PostMapping(path = "/document/pensionerRequestUpload")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> uploadPensionerFile(@RequestHeader("Authorization") String authorization,
                                             @RequestParam("zahtev") MultipartFile zahtev,
                                             @RequestParam("obrada") MultipartFile obrada,
                                             @RequestParam("penzioniCek") MultipartFile penzioniCek,
                                             @RequestParam("licna") MultipartFile licna,
                                             @RequestParam("fotografija") MultipartFile fotografija
    ) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        pensionerStatusRequestService.store(zahtev,username);
        String message = "Uploaded the file successfully: " + zahtev.getOriginalFilename() + '\n';
        pensionerStatusRequestService.store(obrada,username);
        message += "Uploaded the file successfully: " + obrada.getOriginalFilename() + '\n';
        pensionerStatusRequestService.store(penzioniCek,username);
        message += "Uploaded the file successfully: " + penzioniCek.getOriginalFilename() + '\n';
        pensionerStatusRequestService.store(licna,username);
        message += "Uploaded the file successfully: " + licna.getOriginalFilename() + '\n';
        pensionerStatusRequestService.store(fotografija,username);
        message += "Uploaded the file successfully: " + fotografija.getOriginalFilename() + '\n';
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/document/getStudentFile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public ResponseEntity<byte[]> getFile(@RequestHeader("Authorization") String authorization,
                                          @RequestBody SingleStringDTO name) {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        System.out.println(username);
        System.out.println(name.getString());
        StudentStatusRequest studentStatusRequest = studentStatusRequestService.getByNameAndUsername(name.getString(),username);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + studentStatusRequest.getName() + "\"")
                .body(studentStatusRequest.getData());
    }

}
