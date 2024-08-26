package com.example.demo.Repository;

import com.example.demo.Model.Message;
import com.example.demo.Model.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);
    List<Message> findBySenderId(Integer senderId);
    List<Message> findByReceiverId(Integer receiverId);
    Page<Message> findBySenderIdAndContentContaining(Integer senderId, String content, Pageable pageable);
    Page<Message> findByReceiverIdAndContentContaining(Integer receiverId, String content, Pageable pageable);
    List<Message> findBySenderIdAndReceiverId(Integer senderId, Integer receiverId);

}
