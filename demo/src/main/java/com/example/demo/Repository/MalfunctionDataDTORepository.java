package com.example.demo.Repository;

import com.example.demo.DTO.MalfunctionDataDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MalfunctionDataDTORepository extends JpaRepository<MalfunctionDataDTO,Integer> {

    MalfunctionDataDTO save(MalfunctionDataDTO malfunctionDataDTO);
    List<MalfunctionDataDTO> findAll();

}
