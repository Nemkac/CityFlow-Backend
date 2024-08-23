package com.example.demo.Controller;

import com.example.demo.DTO.BalanceDTO;
import com.example.demo.DTO.EditProfileDTO;
import com.example.demo.Model.Card;
import com.example.demo.Model.User;
import com.example.demo.Service.CardService;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserAccountController {
    private static Logger logger = LoggerFactory.getLogger(UserAccountController.class);
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;

    @PostMapping(path = "/Account/updateProfile")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_DRIVER','ROLE_SERVICER','ROLE_HRAdministrator', 'ROLE_Accountant', 'ROLE_ROUTEADMINISTRATOR', 'ROLE_KYCADMINISTRATOR')")
    public ResponseEntity<String>updateProfile(@RequestHeader("Authorization") String authorisation, @RequestBody EditProfileDTO requestBody){
        String bearerToken = authorisation.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        System.out.println(bearerToken);
        User user = userService.FindByUsername(username);
        if(user != null){
            user.setEmail(requestBody.getEmail());
            user.setName(requestBody.getName());
            user.setLastname(requestBody.getLastname());
            user.setDateOfBirth(requestBody.getDateOfBirth());
            user.setPhoneNumber(requestBody.getPhoneNumber());
            user.setUsername(requestBody.getUsername());
            userService.save(user);
            return new ResponseEntity<>("Updated!", HttpStatusCode.valueOf(200));
        } else{
            return new ResponseEntity<>("Invalid request!",HttpStatusCode.valueOf(400));
        }
    }

    @GetMapping(path = "/Account/getUser")
    @PreAuthorize("hasAnyRole('ROLE_USER','','ROLE_SERVICER','ROLE_HRAdministrator', 'ROLE_Accountant')")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String authorisation){
        String bearerToken = authorisation.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        User user = userService.FindByUsername(username);
        if(user != null){
            return new ResponseEntity<User>(user,HttpStatusCode.valueOf(200));

        }else{
            return new ResponseEntity<User>((User) null, HttpStatusCode.valueOf(400));
        }
    }

    //3 nacina placanja. Uzivo, preko kartice(ovaj je ovde), upload uplatnice
    //sutra adminski rucni unos uzivo placanja
    @PostMapping(path = "/Account/updateBalance")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> updateBalance(@RequestHeader("Authorization") String authorisation, @RequestBody BalanceDTO balanceDTO){
        String bearerToken = authorisation.substring(7);
        String username = jwtService.extractUsername(bearerToken);
        User user = userService.FindByUsername(username);
        if(user == null){
            return new ResponseEntity<>("User not found",HttpStatusCode.valueOf(400));
        }
        Card userCard = cardService.findByCardNumber(balanceDTO.getCardNumber());
        if(userCard == null
                || !userCard.getCardDate().equals(balanceDTO.getCardDate())
                || !userCard.getCvs().equals(balanceDTO.getCvs())
                || !userCard.getCardHolder().equals(balanceDTO.getCardHolder())){
            return new ResponseEntity<>("Invalid card data",HttpStatusCode.valueOf(400));
        }
        if(userCard.getBalance() < balanceDTO.getPayment()){
            return new ResponseEntity<>("Insufficent funds",HttpStatusCode.valueOf(400));
        }
        user.seteWallet(user.geteWallet()+balanceDTO.getPayment());
        userCard.setBalance(userCard.getBalance()-balanceDTO.getPayment());
        cardService.save(userCard);
        userService.save(user);
        return new ResponseEntity<>("Balance updated!",HttpStatusCode.valueOf(200));
    }
}

