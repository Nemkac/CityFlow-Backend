package com.example.demo.Service;

import com.example.demo.Model.Document;
import com.example.demo.Repository.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document store(MultipartFile file,String username) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Date now = new Date();
        Document document = new Document(fileName,username, now, file.getContentType(), file.getBytes());
        return documentRepository.save(document);
    }
    public Document getByNameAndUsername(String name,String username){
        return documentRepository.findByNameAndUsername(name,username);
    }
}
