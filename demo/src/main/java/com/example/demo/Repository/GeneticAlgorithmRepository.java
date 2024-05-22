package com.example.demo.Repository;

import com.example.demo.Model.ElectricBus;
import com.example.demo.Model.GeneticAlgorithmOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneticAlgorithmRepository extends JpaRepository<GeneticAlgorithmOutput,Integer> {

    GeneticAlgorithmOutput save(GeneticAlgorithmOutput geneticAlgorithmOutput);

    GeneticAlgorithmOutput getByElBus(ElectricBus bus);


}
