package com.example.demo.Repository;

import com.example.demo.Model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, Integer> {

    List<Widget> findAll();
    Optional<Widget> findById(Integer id);
}
