package com.example.demo.Controller;

import com.example.demo.DTO.BalanceDTO;
import com.example.demo.DTO.KYCBalanceDTO;
import com.example.demo.Model.User;
import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KYCAdministratorController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

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
