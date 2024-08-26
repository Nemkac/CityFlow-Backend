package com.example.demo.DTO;

import com.example.demo.Model.Message;
import com.example.demo.Model.User;

public class CommunicationPartnerDTO {
    private User user;
    private Message lastMessage;

    public CommunicationPartnerDTO(User user, Message lastMessage) {
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public CommunicationPartnerDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
