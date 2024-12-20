package com.example.BelajarSpringboot.BelajarSpringboot.service;

import com.example.BelajarSpringboot.BelajarSpringboot.detail.CustomUserDetails;
import com.example.BelajarSpringboot.BelajarSpringboot.detail.UserDetail;
import com.example.BelajarSpringboot.BelajarSpringboot.model.LoginRequest;
import com.example.BelajarSpringboot.BelajarSpringboot.model.User;
import com.example.BelajarSpringboot.BelajarSpringboot.repository.UserRepository;
import com.example.BelajarSpringboot.BelajarSpringboot.securityNew.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService  {

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    CustomUserDetails customUserDetails;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            return UserDetail.buidUser(userOptional.get());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }


    public Map<String, Object> authenticate(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        UserDetails userDetails = loadUserByUsername(email);

        if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Email atau password yang Anda masukkan salah");
        }

        // Generate token
        String token = jwtTokenUtil.generateToken(userDetails);

        // Adjust response structure
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", ((UserDetail) userDetails).getId());
        userData.put("email", userDetails.getUsername());
        userData.put("role", ((UserDetail) userDetails).getRole());

        Map<String, Object> response = new HashMap<>();
        response.put("data", userData);
        response.put("token", token);

        return response;
    }


}