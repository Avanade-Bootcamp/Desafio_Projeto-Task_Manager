package com.tasks.manager.controller;


import com.tasks.manager.domain.model.User;
import com.tasks.manager.domain.repository.UserRepository;
import com.tasks.manager.security.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("E-mail já registrado!");
        }
        User savedUser = jwtService.registerUser(user.getEmail(), user.getPassword(), user.getName());
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = jwtService.authenticateUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(token);
    }
}
