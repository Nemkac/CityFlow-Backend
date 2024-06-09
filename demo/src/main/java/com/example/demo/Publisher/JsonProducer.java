package com.example.demo.Publisher;

import com.example.demo.DTO.LiveLocationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key-json}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public JsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(LiveLocationDTO liveLocationDTO){
        LOGGER.info(String.format("Json message sent -> %s", liveLocationDTO.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, liveLocationDTO);
    }
}
