package com.example.demo.Controller;


import com.example.demo.DTO.SingleStringDTO;
import com.example.demo.Model.Document;
import com.example.demo.Service.DocumentService;
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
public class DocumentationController {
    @Autowired
    public DocumentService documentService;
    @Autowired
    public JwtService jwtService;


    @PostMapping(path = "/document/uploadFile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> uploadFile(@RequestHeader("Authorization") String authorization,
                                             @RequestParam("zahtev") MultipartFile file1,
                                             @RequestParam("obrada") MultipartFile file2
    ) throws IOException {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);

        documentService.store(file1,username);
        String message = "Uploaded the file successfully: " + file1.getOriginalFilename() + '\n';
        documentService.store(file2,username);
        message += "Uploaded the file successfully: " + file2.getOriginalFilename() + '\n';
        return ResponseEntity.status(200).body(message);
    }

    @GetMapping("/document/getFile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Transactional
    public ResponseEntity<byte[]> getFile(@RequestHeader("Authorization") String authorization,
                                          @RequestBody SingleStringDTO name) {
        String bearerToken = authorization.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        System.out.println(username);
        System.out.println(name.getString());
        Document document = documentService.getByNameAndUsername(name.getString(),username);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                .body(document.getData());
    }

}
