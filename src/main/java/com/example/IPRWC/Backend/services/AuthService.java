package com.example.IPRWC.Backend.services;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> Login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> register(SignupRequest signupRequest) {
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }
        User user = new User(signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getFirstName(), signupRequest.getPrefix(),
                signupRequest.getLastName(), signupRequest.getStreet(), signupRequest.getZipCode(), signupRequest.getHouseNumber(),
                signupRequest.getPhoneNumber(), signupRequest.getPlace());

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
