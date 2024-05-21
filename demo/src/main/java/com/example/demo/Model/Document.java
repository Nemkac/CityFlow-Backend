package com.example.demo.Model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private Date date;

    @Column
    private String type;

    @Column
    @Lob
    private byte[] data;

    public Document(String name, String username, Date date, String type, byte[] data) {
        this.name = name;
        this.username = username;
        this.date = date;
        this.type = type;
        this.data = data;
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}