package com.example.demo.Controller;


import com.example.demo.DTO.SingleStringDTO;
import com.example.demo.Model.HealthcareRequest;
import com.example.demo.Model.PensionerStatusRequest;
import com.example.demo.Model.StudentStatusRequest;
import com.example.demo.Model.VacationRequest;
import com.example.demo.Service.*;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;

@RestController
public class DocumentController {
    @Autowired
    public StudentStatusRequestService studentStatusRequestService;
    @Autowired
    public PensionerStatusRequestService pensionerStatusRequestService;
    @Autowired
    public HealthcareService healthcareService;
    @Autowired
    public VacationService vacationService;
    @Autowired
    public FileService fileService;
    @Autowired
    public JwtService jwtService;

    //403 ako se ne posalju svi fajlovi
    @Transactional
    @PostMapping(path = "/document/studentRequestUpload")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> uploadStudentFile(@RequestHeader("Authorization") String authorization,
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

    @Transactional
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
    @Transactional
    @PostMapping(path = "/document/healthcareRequestUpload")
    //za sad se testira sa userom, kasnije cu staviti ostale role.
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> uploadHealthcareFile(@RequestHeader("Authorization") String authorization,
                                                      @RequestParam("zahtev") MultipartFile zahtev
    ) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        healthcareService.store(zahtev,username);
        String message = "Uploaded the file successfully: " + zahtev.getOriginalFilename() + '\n';
        return ResponseEntity.status(200).body(message);
    }
    @Transactional
    @PostMapping(path = "/document/vacationRequestUpload")
    //za sad se testira sa userom, kasnije cu staviti ostale role.
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> uploadVacationFile(@RequestHeader("Authorization") String authorization,
                                                       @RequestParam("zahtev") MultipartFile zahtev
    ) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        vacationService.store(zahtev,username);
        String message = "Uploaded the file successfully: " + zahtev.getOriginalFilename() + '\n';
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/document/getStudentFiles")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public ResponseEntity<String> getStudentFiles(@RequestHeader("Authorization") String authorization) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        StudentStatusRequest[] studentStatusRequest = studentStatusRequestService.getAllByUsername(username);
        for (StudentStatusRequest request : studentStatusRequest) {
            fileService.saveStudentFiles(request.getData(),request.getName());
        }
        return ResponseEntity.status(200).body("Files written!");
    }
    @GetMapping("/document/getPensionerFiles")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public ResponseEntity<String> getPensionerFiles(@RequestHeader("Authorization") String authorization) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        PensionerStatusRequest[] pensionerStatusRequests = pensionerStatusRequestService.getAllByUsername(username);
        for (PensionerStatusRequest request : pensionerStatusRequests) {
            fileService.savePensionerFiles(request.getData(),request.getName());
        }
        return ResponseEntity.status(200).body("Files written!");
    }
    @GetMapping("/document/getVacationFiles")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public ResponseEntity<String> getVacationFiles(@RequestHeader("Authorization") String authorization) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        VacationRequest[] vacationRequests = vacationService.getAllByUsername(username);
        for (VacationRequest request : vacationRequests) {
            fileService.saveVacationFiles(request.getData(),request.getName());
        }
        return ResponseEntity.status(200).body("Files written!");
    }

    @GetMapping("/document/getHealthcareFiles")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public ResponseEntity<String> getHealthcareFiles(@RequestHeader("Authorization") String authorization) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        HealthcareRequest[] healthcareRequests = healthcareService.getAllByUsername(username);
        for (HealthcareRequest request : healthcareRequests) {
            fileService.saveHealthcareFiles(request.getData(),request.getName());
        }
        return ResponseEntity.status(200).body("Files written!");
    }


}
