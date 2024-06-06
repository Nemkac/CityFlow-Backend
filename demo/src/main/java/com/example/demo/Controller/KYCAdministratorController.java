package com.example.demo.Controller;

import com.example.demo.DTO.KYCBalanceDTO;
import com.example.demo.DTO.UsernamesDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;


@RestController
public class KYCAdministratorController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/KYC/helloPython")
    @PreAuthorize("hasAuthority('ROLE_KYCADMINISTRATOR')")
    public ResponseEntity<String> hello(@RequestBody UsernamesDTO usernamesDTO){
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("usernames",usernamesDTO.getUsernames());
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8000/readDox";
        return restTemplate.postForEntity(url,map,String.class);
    }

    @PostMapping(path = "/KYC/updateBalance")
    @PreAuthorize("hasAuthority('ROLE_KYCADMINISTRATOR')")
    public ResponseEntity<String> updateBalance(@RequestBody KYCBalanceDTO balanceDTO) {
        User user = userService.FindByUsername(balanceDTO.getUsername());
        if(user!= null) {
            user.seteWallet(user.geteWallet() + balanceDTO.getPayment());
            userService.save(user);
            return new ResponseEntity<>("Balance updated!", HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity<>("Error!",HttpStatusCode.valueOf(400));
        }
    }

}
