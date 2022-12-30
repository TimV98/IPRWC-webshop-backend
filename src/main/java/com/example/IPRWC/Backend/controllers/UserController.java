package com.example.IPRWC.Backend.controllers;

import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/info")
    public User getUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).get();
    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("/edit")
    public User editUserDetails(@RequestBody User user) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userData = userRepository.findByEmail(email);
        if (userData.isPresent() && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            User _user = userData.get();
            _user.setFirstName(user.getFirstName());
            _user.setPrefix(user.getPrefix());
            _user.setLastName(user.getLastName());
            _user.setAddress(user.getAddress());
            _user.setZipCode(user.getZipCode());
            _user.setHouseNumber(user.getHouseNumber());

            return userRepository.save(_user);
        }
        return null;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
