package com.example.demo.DTO;

import java.util.List;

public class UsernamesDTO {

    private List<String> usernames;

    public UsernamesDTO(List<String> usernames) {
        this.usernames = usernames;
    }

    public UsernamesDTO() {
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
