package com.example.demo.Repository;

import com.example.demo.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCardNumber(String number);

}
