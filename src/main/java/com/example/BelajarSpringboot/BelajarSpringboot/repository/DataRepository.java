package com.example.BelajarSpringboot.BelajarSpringboot.repository;

import com.example.BelajarSpringboot.BelajarSpringboot.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByUserId(Long idUser);
}
