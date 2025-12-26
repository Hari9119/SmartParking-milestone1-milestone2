package com.parksmart.backend.controller;

import com.parksmart.backend.model.*;
import com.parksmart.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.existsByUsername(user.getUsername()))
            return ResponseEntity.badRequest().body("Username exists");

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRole() == null)
            user.setRole(Role.USER);

        userRepository.save(user);
        return ResponseEntity.ok("REGISTERED");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User req) {

        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            return ResponseEntity.status(401).body("INVALID_CREDENTIALS");

        return ResponseEntity.ok(
                Map.of(
                        "username", user.getUsername(),
                        "role", user.getRole()
                )
        );
    }
}
