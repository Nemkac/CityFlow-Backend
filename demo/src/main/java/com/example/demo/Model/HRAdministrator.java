package com.example.demo.Model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "HRAdministrator")
public class HRAdministrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer hrAdminId;

    @OneToOne
    private User user;

    public HRAdministrator() {
    }

    public HRAdministrator(User user) {
        this.user = user;
    }

    public Integer getHrAdminId() {
        return hrAdminId;
    }

    public void setHrAdminId(Integer hrAdminId) {
        this.hrAdminId = hrAdminId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
