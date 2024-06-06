package com.example.demo.Controller;

import com.example.demo.DTO.LiveLocationDTO;
import com.example.demo.Publisher.JsonProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/CityFlow")
public class LiveLocationController {

    private JsonProducer rabbitMQJsonProducer;

    public LiveLocationController(JsonProducer rabbitMQJsonProducer) {
        //this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping(value = "/liveLocation/publish/json")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<LiveLocationDTO> sendJsonMessage(@RequestBody LiveLocationDTO liveLocationDTO){
        rabbitMQJsonProducer.sendJsonMessage(liveLocationDTO);
        return new ResponseEntity(liveLocationDTO, HttpStatus.OK);
    }

}