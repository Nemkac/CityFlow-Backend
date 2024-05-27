package com.example.demo.Service;

import com.example.demo.Model.Card;
import com.example.demo.Repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;


    public Card findByCardNumber(String number){
        return cardRepository.findByCardNumber(number);
    }
    public Card addCard(Card card){ return cardRepository.save(card);};
    public Card save(Card card){ return cardRepository.save(card);};
}
