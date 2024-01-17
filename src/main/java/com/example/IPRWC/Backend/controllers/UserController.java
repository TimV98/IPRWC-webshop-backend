package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.payload.dto.UserDTO;
import com.example.IPRWC.Backend.repository.UserRepository;
import com.example.IPRWC.Backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://93.119.4.217", "http://s1108697"}, allowCredentials = "true")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/info")
    public ResponseEntity<?> getUserDetails() {
        return userService.getUserDetails();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("User with ID not found" + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit/me")
    public ResponseEntity<?> editProfile(@Valid @RequestBody UserDTO user) {
        return this.userService.editProfile(user);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editUser(@PathVariable long id, @Valid @RequestBody UserDTO user) {
        return this.userService.adminEditUser(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return this.userService.deleteUser(id);
    }
}
