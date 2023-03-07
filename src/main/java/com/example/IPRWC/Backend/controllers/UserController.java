package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/info")
    public User getUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).get();
    }


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>("User with ID not found" + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUserDetails(@RequestBody User user) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userData = userRepository.findByEmail(email);
        if (userData.isPresent() && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            User _user = userData.get();
            _user.setFirstName(user.getFirstName());
            _user.setPrefix(user.getPrefix());
            _user.setLastName(user.getLastName());
            _user.setStreet(user.getStreet());
            _user.setZipCode(user.getZipCode());
            _user.setHouseNumber(user.getHouseNumber());
            _user.setPhoneNumber(user.getPhoneNumber());
            _user.setPlace(user.getPlace());

            userRepository.save(_user);
            return new ResponseEntity<>("User Edited!", HttpStatus.OK);
        } else if (userData.isEmpty()) {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("edit/{id}")
    public ResponseEntity<?> editUserDetails(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            System.out.println("Hij werkt!");
            User _user = userData.get();
            _user.setFirstName(user.getFirstName());
            _user.setPrefix(user.getPrefix());
            _user.setLastName(user.getLastName());
            _user.setStreet(user.getStreet());
            _user.setZipCode(user.getZipCode());
            _user.setHouseNumber(user.getHouseNumber());
            _user.setPhoneNumber(user.getPhoneNumber());
            _user.setPlace(user.getPlace());

            userRepository.save(_user);
            return new ResponseEntity<>("User edited", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<>("User deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User doesn't exist!", HttpStatus.NOT_FOUND);

    }
}
