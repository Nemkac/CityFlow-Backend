package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    List<User> findByRoles(String role);
    @Query("SELECT u FROM User u WHERE LOWER(CONCAT(u.name, ' ', u.lastname)) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<User> searchByName(String name);
    @Query("SELECT u FROM User u WHERE LOWER(REPLACE(SUBSTRING(u.roles, 6), '_', ' ')) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<User> findByRoleContaining(String roleFragment);

    int countByEmployedTrue();




}
