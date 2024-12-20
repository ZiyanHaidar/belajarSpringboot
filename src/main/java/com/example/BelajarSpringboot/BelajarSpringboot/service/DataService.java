package com.example.BelajarSpringboot.BelajarSpringboot.service;

import com.example.BelajarSpringboot.BelajarSpringboot.DTO.DataDTO;
import com.example.BelajarSpringboot.BelajarSpringboot.model.Data;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface DataService {
    List<Data> getAllData();

    List<Data> getAllByUser(Long idUser);

    Optional<Data> getDataById(Long id);

    DataDTO tambahDataDTO(Long idUser, DataDTO dataDTO);

    DataDTO editDataDTO(Long id, Long idUser, DataDTO dataDTO) throws IOException;

    void deleteData(Long id) throws IOException;
}

