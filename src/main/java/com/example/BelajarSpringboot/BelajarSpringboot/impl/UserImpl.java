package com.example.BelajarSpringboot.BelajarSpringboot.impl;

import com.example.BelajarSpringboot.BelajarSpringboot.DTO.PasswordDTO;
import com.example.BelajarSpringboot.BelajarSpringboot.exception.BadRequestException;
import com.example.BelajarSpringboot.BelajarSpringboot.exception.NotFoundException;
import com.example.BelajarSpringboot.BelajarSpringboot.model.User;
import com.example.BelajarSpringboot.BelajarSpringboot.repository.UserRepository;
import com.example.BelajarSpringboot.BelajarSpringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public UserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        return userRepository.save(admin);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id User Tidak Ditemukan"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User edit(Long id, User admin) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User Tidak Ditemukan"));

        existingUser.setUsername(admin.getUsername());
        existingUser.setEmail(admin.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public User putPasswordUser(PasswordDTO passwordDTO, Long id) {
        User update = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Id Tidak Ditemukan"));

        boolean isOldPasswordCorrect = encoder.matches(passwordDTO.getOld_password(), update.getPassword());

        if (!isOldPasswordCorrect) {
            throw new NotFoundException("Password Lama Tidak Sesuai");
        }

        if (passwordDTO.getNew_password().equals(passwordDTO.getConfirm_new_password())) {
            update.setPassword(encoder.encode(passwordDTO.getNew_password()));
            return userRepository.save(update);
        } else {
            throw new BadRequestException("Password Tidak Sesuai");
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Map<String, Boolean> response = new HashMap<>();
        try {
            userRepository.deleteById(id);
            response.put("Deleted", Boolean.TRUE);
        } catch (Exception e) {
            response.put("Deleted", Boolean.FALSE);
        }
        return response;
    }
}