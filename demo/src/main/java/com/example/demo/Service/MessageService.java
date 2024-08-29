package com.example.demo.Service;

import com.example.demo.Model.Message;
import com.example.demo.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message sendMessage(Integer senderId, Integer receiverId, String content) {
        Message message = new Message(senderId, receiverId, content, LocalDateTime.now(), false);
        return messageRepository.save(message);
    }

    public Page<Message> getMessagesForUserWithPaginationAndFilter(Integer userId, String filter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findByReceiverIdAndContentContaining(userId, filter, pageable);
    }

    public Page<Message> getSentMessagesWithPaginationAndFilter(Integer userId, String filter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findBySenderIdAndContentContaining(userId, filter, pageable);
    }

    public List<Message> getMessagesBetweenUsers(Integer userId1, Integer userId2) {
        List<Message> sentMessages = messageRepository.findBySenderIdAndReceiverId(userId1, userId2);
        List<Message> receivedMessages = messageRepository.findBySenderIdAndReceiverId(userId2, userId1);
        List<Message> allMessages = new ArrayList<>(sentMessages);
        allMessages.addAll(receivedMessages);
        allMessages.sort(Comparator.comparing(Message::getTimestamp));
        return allMessages;
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
    public List<Message> getSentMessages(Integer userId) {
        return messageRepository.findBySenderId(userId);
    }

    public List<Message> getReceivedMessages(Integer userId) {
        return messageRepository.findByReceiverId(userId);
    }

}