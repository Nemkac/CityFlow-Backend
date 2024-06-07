package com.example.demo.Controller;

import com.example.demo.DTO.KYCBalanceDTO;
import com.example.demo.DTO.UsernamesDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class KYCAdministratorController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/KYC/studentRequests")
    @PreAuthorize("hasAuthority('ROLE_KYCADMINISTRATOR')")
    public ResponseEntity<String> studentRequests(@RequestBody UsernamesDTO usernamesDTO){
        RestTemplate restTemplate = new RestTemplate();
        int counter = 0;
        String url = "http://localhost:8000/studentRequests";
        for (String username: usernamesDTO.getUsernames()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("usernames",username);
            HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<>(map);
            List<String> lista =  restTemplate.exchange(url, HttpMethod.POST,requestEntity, new ParameterizedTypeReference<List<String>>() {}).getBody();
            for (String linija : lista) {
                if(linija.contains("JMBG")){
                    counter++;
                    String ignoreSpace = linija.replace(" ", "");
                    ignoreSpace = ignoreSpace.replace(":","");
                    ignoreSpace = ignoreSpace.replace("JMBG", "");
                    System.out.println(ignoreSpace);
                    if(ignoreSpace.length() == 13) {
                        User tmp = userService.FindByUsername(username);
                        if(tmp != null){
                            tmp.setStatus("Student");
                            userService.save(tmp);
                        }
                    }
                }
            }
        }

        return new ResponseEntity<>("Validirano: " + counter+" korisnika",HttpStatusCode.valueOf(200));
    }
    @PostMapping(path = "/KYC/pensionerRequests")
    @PreAuthorize("hasAuthority('ROLE_KYCADMINISTRATOR')")
    public ResponseEntity<String> pensionerRequests(@RequestBody UsernamesDTO usernamesDTO){
        RestTemplate restTemplate = new RestTemplate();
        int counter = 0;
        String url = "http://localhost:8000/pensionerRequests";
        for (String username: usernamesDTO.getUsernames()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("usernames", username);
            HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<>(map);
            List<String> lista = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<List<String>>() {
            }).getBody();
            ArrayList<String> pibs = new ArrayList<>();
            for (String linija : lista) {
                if (linija.contains("PIB")) {
                    counter++;
                    String ignoreSpace = linija.replace(" ", "");
                    ignoreSpace = ignoreSpace.replace(":", "");
                    ignoreSpace = ignoreSpace.replace("PIB", "");
                    pibs.add(ignoreSpace);
                }
            }
            if (!pibs.isEmpty()) {
                String tmp = pibs.get(0);
                boolean flag = false;
                for (String pib : pibs) {
                    System.out.println(pib);
                    if (!pib.equals(tmp)) {
                        flag = true;
                    }
                }
                if (!flag) {
                    User user = userService.FindByUsername(username);
                    if (user != null) {
                        user.setStatus("Pensioner");
                        userService.save(user);
                    }
                }
            }
        }
        return new ResponseEntity<>("Validirano: " + counter+" korisnika",HttpStatusCode.valueOf(200));
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
