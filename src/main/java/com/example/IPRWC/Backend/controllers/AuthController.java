package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.Role;
import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.models.ERole;
import com.example.IPRWC.Backend.payload.request.LoginRequest;
import com.example.IPRWC.Backend.payload.request.SignupRequest;
import com.example.IPRWC.Backend.payload.response.JwtResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.RoleRepository;
import com.example.IPRWC.Backend.repository.UserRepository;
import com.example.IPRWC.Backend.security.JwtUtil;
import com.example.IPRWC.Backend.security.UserDetailsImpl;
import com.example.IPRWC.Backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerHandler(@RequestBody SignupRequest signupRequest) {
        return authService.register(signupRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginRequest loginRequest) {
        return authService.Login(loginRequest);
    }
}
