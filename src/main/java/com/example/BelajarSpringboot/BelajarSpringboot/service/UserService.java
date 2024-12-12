package com.example.BelajarSpringboot.BelajarSpringboot.service;


import com.example.BelajarSpringboot.BelajarSpringboot.DTO.PasswordDTO;
import com.example.BelajarSpringboot.BelajarSpringboot.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User registerUser(User admin);

    User getById(Long id);

    List<User> getAll();

    User edit(Long id, User admin);

    User putPasswordUser(PasswordDTO passwordDTO, Long id);

    Map<String, Boolean> delete(Long id);
}