package com.example.demo.Controller;

import com.example.demo.DTO.GeneticAlgorithmInputDTO;
import com.example.demo.Model.ChargingStation;
import com.example.demo.Model.ElectricBus;
import com.example.demo.Model.GeneticAlgorithmOutput;
import com.example.demo.Service.ChargingStationService;
import com.example.demo.Service.ElectricBusService;
import com.example.demo.Service.GeneticAlgorithmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GeneticAlgorithmCommunicationController {

    @Autowired
    private ElectricBusService electricBusService;

    @Autowired
    private ChargingStationService chargingStationService;

    @Autowired
    private GeneticAlgorithmService geneticAlgorithmService;

    @Autowired
    private BusController busController;

    @GetMapping(value="/CityFlow/testGenetic")
    public ResponseEntity<GeneticAlgorithmOutput[]> testGenetic() throws JsonProcessingException {
        ElectricBus elBus1 = this.electricBusService.getById(1);
        ElectricBus elBus2 = this.electricBusService.getById(2);
        ChargingStation chargingStation1 = this.chargingStationService.getById(1);
        ChargingStation chargingStation2 = this.chargingStationService.getById(2);
        List<ElectricBus> buses = new ArrayList<ElectricBus>();
        List<ChargingStation> stations = new ArrayList<ChargingStation>();
        buses.add(elBus1);
        buses.add(elBus2);
        stations.add(chargingStation1);
        stations.add(chargingStation2);
        GeneticAlgorithmInputDTO geneticInput = new GeneticAlgorithmInputDTO(buses,stations);

        final String uri =  "http://localhost:5000/runGeneticAlgorithm";
        RestTemplate restTemplate = new RestTemplate();
        //List<GeneticAlgorithmOutput> output = restTemplate.getForObject(uri,GeneticAlgorithmOutput.class);
        //ResponseEntity<String> output = restTemplate.getForEntity(uri,String.class);
        //ResponseEntity<List<GeneticAlgorithmOutput>> output = restTemplate.postForEntity(uri,GeneticAlgorithmInputDTO.class);
        GeneticAlgorithmOutput[] output = restTemplate.postForEntity(uri,geneticInput,GeneticAlgorithmOutput[].class).getBody();
        return new ResponseEntity<>(output,HttpStatus.OK);

    }

    @GetMapping(value="/CityFlow/niguh")
    public ResponseEntity<GeneticAlgorithmInputDTO> niguh() throws JsonProcessingException {
        ElectricBus elBus1 = this.electricBusService.getById(1);
        ElectricBus elBus2 = this.electricBusService.getById(2);
        ChargingStation chargingStation1 = this.chargingStationService.getById(1);
        ChargingStation chargingStation2 = this.chargingStationService.getById(2);
        List<ElectricBus> buses = new ArrayList<ElectricBus>();
        List<ChargingStation> stations = new ArrayList<ChargingStation>();
        buses.add(elBus1);
        buses.add(elBus2);
        stations.add(chargingStation1);
        stations.add(chargingStation2);
        GeneticAlgorithmInputDTO geneticInput = new GeneticAlgorithmInputDTO(buses, stations);
        return new ResponseEntity<>(geneticInput, HttpStatus.OK);
    }


    @GetMapping(value="/CityFlow/testGeneticString")
    public ResponseEntity<GeneticAlgorithmOutput[]> testGeneticString() throws JsonProcessingException {
        this.busController.databaseFill();
        ElectricBus elBus1 = this.electricBusService.getById(1);
        ElectricBus elBus2 = this.electricBusService.getById(2);
        ElectricBus elBus3 = this.electricBusService.getById(3);
        ElectricBus elBus4 = this.electricBusService.getById(4);
        ElectricBus elBus5 = this.electricBusService.getById(5);
        ElectricBus elBus6 = this.electricBusService.getById(6);
        ElectricBus elBus7 = this.electricBusService.getById(7);
        ElectricBus elBus8 = this.electricBusService.getById(8);
        ElectricBus elBus9 = this.electricBusService.getById(9);
        ChargingStation chargingStation1 = this.chargingStationService.getById(1);
        ChargingStation chargingStation2 = this.chargingStationService.getById(2);
        ChargingStation chargingStation3 = this.chargingStationService.getById(3);
        ChargingStation chargingStation4 = this.chargingStationService.getById(4);
        List<ElectricBus> buses = new ArrayList<ElectricBus>();
        List<ChargingStation> stations = new ArrayList<ChargingStation>();
        buses.add(elBus1);
        buses.add(elBus2);
        buses.add(elBus3);
        buses.add(elBus4);
        buses.add(elBus5);
        buses.add(elBus6);
        buses.add(elBus7);
        buses.add(elBus8);
        buses.add(elBus9);
        stations.add(chargingStation1);
        stations.add(chargingStation2);
        //stations.add(chargingStation3);
        //stations.add(chargingStation4);
        GeneticAlgorithmInputDTO geneticInput = new GeneticAlgorithmInputDTO(buses,stations);

        final String uri =  "http://localhost:5000/runGeneticAlgorithm";
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(uri,geneticInput,String.class);
        System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        GeneticAlgorithmOutput[] outputs = objectMapper.readValue(response.getBody(),GeneticAlgorithmOutput[].class);
        for(GeneticAlgorithmOutput output : outputs) {
            this.geneticAlgorithmService.save(output);
            System.out.println(LocalDateTime.parse(output.getStartTime(),DateTimeFormatter.ISO_DATE_TIME));
        }
        return new ResponseEntity<>(outputs,HttpStatus.OK);

    }


}
