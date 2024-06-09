package com.example.demo.Consumer;

import com.example.demo.DTO.LiveLocationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
@Controller
public class JsonConsumer {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public JsonConsumer(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = "liveLocationJSON_queue")
    @MessageMapping("/send/liveLocation")
    public void consumeJsonMessage(LiveLocationDTO liveLocationDTO) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(liveLocationDTO);
        simpMessagingTemplate.convertAndSend("/livelocation-websocket", json);
        LOGGER.info(String.format("Received JSON message -> %s", liveLocationDTO.toString()));
    }
}
