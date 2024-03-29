package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.payload.request.LoginRequest;
import com.example.IPRWC.Backend.payload.request.SignupRequest;
import com.example.IPRWC.Backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://93.119.4.217", "http://s1108697.nl"}, allowCredentials = "true")

public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerHandler(@RequestBody SignupRequest signupRequest) {
        return authService.register(signupRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
}
