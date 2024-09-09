package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "widget")
public class Widget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column
    public String name;

    public Widget() {
    }

    public Widget(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
