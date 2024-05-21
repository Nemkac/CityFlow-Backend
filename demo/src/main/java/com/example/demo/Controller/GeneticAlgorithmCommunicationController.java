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

        ResponseEntity<String> response = restTemplate.postForEntity(uri,geneticInput,String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        GeneticAlgorithmOutput[] outputs = objectMapper.readValue(response.getBody(),GeneticAlgorithmOutput[].class);
        for(GeneticAlgorithmOutput output : outputs) {
            this.geneticAlgorithmService.save(output);
        }
        return new ResponseEntity<>(outputs,HttpStatus.OK);



    }


}
