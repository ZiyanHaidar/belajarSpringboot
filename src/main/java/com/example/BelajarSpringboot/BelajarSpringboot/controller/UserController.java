package com.example.BelajarSpringboot.BelajarSpringboot.controller;

import com.example.BelajarSpringboot.BelajarSpringboot.DTO.PasswordDTO;
import com.example.BelajarSpringboot.BelajarSpringboot.exception.CommonResponse;
import com.example.BelajarSpringboot.BelajarSpringboot.exception.ResponseHelper;
import com.example.BelajarSpringboot.BelajarSpringboot.model.User;
import com.example.BelajarSpringboot.BelajarSpringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.edit(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping("/edit-password/{id}")
    public CommonResponse<User> putPassword(@RequestBody PasswordDTO password, @PathVariable Long id) {
        return ResponseHelper.ok(userService.putPasswordUser(password, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        Map<String, Boolean> response = userService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}