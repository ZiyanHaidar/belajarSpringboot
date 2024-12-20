package com.example.BelajarSpringboot.BelajarSpringboot.impl;

import com.example.BelajarSpringboot.BelajarSpringboot.DTO.DataDTO;
import com.example.BelajarSpringboot.BelajarSpringboot.exception.NotFoundException;
import com.example.BelajarSpringboot.BelajarSpringboot.model.Data;
import com.example.BelajarSpringboot.BelajarSpringboot.model.User;
import com.example.BelajarSpringboot.BelajarSpringboot.repository.DataRepository;
import com.example.BelajarSpringboot.BelajarSpringboot.repository.UserRepository;
import com.example.BelajarSpringboot.BelajarSpringboot.service.DataService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DataImpl implements DataService {

    private final DataRepository dataRepository;
    private final UserRepository userRepository;

    public DataImpl(DataRepository dataRepository, UserRepository userRepository) {
        this.dataRepository = dataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Data> getAllData() {
        return dataRepository.findAll();
    }

    @Override
    public List<Data> getAllByUser(Long idUser) {
        return dataRepository.findByUserId(idUser);
    }

    @Override
    public Optional<Data> getDataById(Long id) {
        return dataRepository.findById(id); // Menggunakan bawaan JpaRepository
    }

    @Override
    public DataDTO tambahDataDTO(Long idUser, DataDTO dataDTO) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("User dengan ID " + idUser + " tidak ditemukan"));

        Data data = new Data();
        data.setUser(user);
        data.setDataMakanan(dataDTO.getDataMakanan());
        data.setDataMinuman(dataDTO.getDataMinuman());
        data.setDataPakaian(dataDTO.getDataPakaian());
        data.setDataKeluarga(dataDTO.getDataKeluarga());
        data.setDataSekolah(dataDTO.getDataSekolah());

        Data savedData = dataRepository.save(data);

        DataDTO result = new DataDTO();
        result.setId(savedData.getId());
        result.setIdUser(user.getId());
        result.setDataMakanan(savedData.getDataMakanan());
        result.setDataMinuman(savedData.getDataMinuman());
        result.setDataPakaian(savedData.getDataPakaian());
        result.setDataKeluarga(savedData.getDataKeluarga());
        result.setDataSekolah(savedData.getDataSekolah());

        return result;
    }

    @Override
    public DataDTO editDataDTO(Long id, Long idUser, DataDTO dataDTO) throws IOException {
        Data existingData = dataRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Data tidak ditemukan"));

        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NotFoundException("User dengan ID " + idUser + " tidak ditemukan"));

        existingData.setUser(user);
        existingData.setDataMakanan(dataDTO.getDataMakanan());
        existingData.setDataMinuman(dataDTO.getDataMinuman());
        existingData.setDataPakaian(dataDTO.getDataPakaian());
        existingData.setDataKeluarga(dataDTO.getDataKeluarga());
        existingData.setDataSekolah(dataDTO.getDataSekolah());

        Data updatedData = dataRepository.save(existingData);

        DataDTO result = new DataDTO();
        result.setId(updatedData.getId());
        result.setIdUser(user.getId());
        result.setDataMakanan(updatedData.getDataMakanan());
        result.setDataMinuman(updatedData.getDataMinuman());
        result.setDataPakaian(updatedData.getDataPakaian());
        result.setDataKeluarga(updatedData.getDataKeluarga());
        result.setDataSekolah(updatedData.getDataSekolah());

        return result;
    }

    @Override
    public void deleteData(Long id) throws IOException {
        dataRepository.deleteById(id);
    }
}

