package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User save(User user);
    User getByUsername(String username);
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    void deleteById(Integer userId);
    User getById(Integer userId);

    List<User> findAll();
    Optional<User> findById(Integer id);

}
