package com.example.BelajarSpringboot.BelajarSpringboot.controller;

import com.example.BelajarSpringboot.BelajarSpringboot.DTO.DataDTO;
import com.example.BelajarSpringboot.BelajarSpringboot.model.Data;
import com.example.BelajarSpringboot.BelajarSpringboot.service.DataService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/data/all")
    public ResponseEntity<List<Data>> getAllData() {
        List<Data> dataList = dataService.getAllData();
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/data/getAllByUser/{idUser}")
    public ResponseEntity<List<Data>> getAllByUser(@PathVariable Long idUser) {
        List<Data> dataList = dataService.getAllByUser(idUser);
        return ResponseEntity.ok(dataList);
    }

    @GetMapping("/data/getById/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable Long id) {
        Optional<Data> data = dataService.getDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/data/tambah/{idUser}")
    public ResponseEntity<DataDTO> tambahData(
            @PathVariable Long idUser,
            @RequestBody DataDTO dataDTO) {
        DataDTO savedData = dataService.tambahDataDTO(idUser, dataDTO);
        return ResponseEntity.ok(savedData);
    }

    @PutMapping(value = "/data/editById/{id}")
    public ResponseEntity<DataDTO> editAnggota(
            @PathVariable Long id,
            @RequestParam Long idAdmin,
            @RequestPart(value = "anggota") DataDTO anggotaDTO) throws IOException {

        DataDTO updatedAnggota = dataService.editDataDTO(id, idAdmin, anggotaDTO);
        return ResponseEntity.ok(updatedAnggota);
    }

    @DeleteMapping("/data/delete/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) throws IOException {
        dataService.deleteData(id);
        return ResponseEntity.noContent().build();
    }
}
