package com.example.demo.Service;

import com.example.demo.Exceptions.RouteNotFoundException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Model.UserInfoDetails;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        //return this.userRepository.save(user);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User FindByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UserNotFoundException("User could not be found by given username: " + username));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = userRepository.findByUsername(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}
