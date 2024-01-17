package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.User;
import com.example.IPRWC.Backend.payload.dto.UserDTO;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<?> getUserDetails() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByEmail(email).isPresent()) {
            User userData = userRepository.findByEmail(email).get();
            UserDTO user = buildUser(userData);
            return ResponseEntity.ok().body(user);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    private UserDTO buildUser(User userData) {
        UserDTO user = UserDTO.builder()
                .id(userData.getId())
                .firstName(userData.getFirstName())
                .prefix(userData.getPrefix())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .zipCode(userData.getZipCode())
                .place(userData.getPlace())
                .street(userData.getStreet())
                .houseNumber(userData.getHouseNumber())
                .phoneNumber(userData.getPhoneNumber())
                .build();

        return user;
    }

    public ResponseEntity<?> getAllUsers() {
        if (!userRepository.findAll().isEmpty()) {
            List<User> users = userRepository.findAll();
            List<UserDTO> usersList = new ArrayList<>();
            for (var user : users) {
                UserDTO userDTO = UserDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .prefix(user.getPrefix())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .zipCode(user.getZipCode())
                        .place(user.getPlace())
                        .street(user.getStreet())
                        .houseNumber(user.getHouseNumber())
                        .phoneNumber(user.getPhoneNumber())
                        .build();

                usersList.add(userDTO);
            }
            return ResponseEntity.ok().body(usersList);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "No users found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> getUserById(long id) {
        if (userRepository.findById(id).isPresent()) {
            User userData = userRepository.findById(id).get();
            UserDTO user = buildUser(userData);
            return ResponseEntity.ok().body(user);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User with ID not found" + id, HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> editProfile(UserDTO user) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByEmail(email).isPresent()) {
            if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                User userData = userRepository.findByEmail(email).get()
                        .withEmail(user.getEmail())
                        .withFirstName(user.getFirstName())
                        .withPrefix(user.getPrefix())
                        .withLastName(user.getLastName())
                        .withZipCode(user.getZipCode())
                        .withPlace(user.getPlace())
                        .withStreet(user.getStreet())
                        .withHouseNumber(user.getHouseNumber())
                        .withPhoneNumber(user.getPhoneNumber());

                userRepository.save(userData);
                return new ResponseEntity<>(new MessageResponse("Profile Edited!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                    "You're unauthorized to access this resource",
                    HttpStatus.UNAUTHORIZED.name()), HttpStatus.UNAUTHORIZED);
        } else if (userRepository.findByEmail(email).isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    "User not found!",
                    HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Something went wrong!",
                HttpStatus.UNPROCESSABLE_ENTITY.name()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<?> adminEditUser(long id, UserDTO user) {
        if (userRepository.findById(id).isPresent()) {
            User editedUser = userRepository.findById(id).get()
                    .withEmail(user.getEmail())
                    .withFirstName(user.getFirstName())
                    .withPrefix(user.getPrefix())
                    .withLastName(user.getLastName())
                    .withZipCode(user.getZipCode())
                    .withPlace(user.getPlace())
                    .withStreet(user.getStreet())
                    .withHouseNumber(user.getHouseNumber())
                    .withPhoneNumber(user.getPhoneNumber());

            userRepository.save(editedUser);

            return ResponseEntity.ok().body("User with id: " + id + " Edited");

        } else if (userRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    "User not found!",
                    HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Something went wrong!",
                HttpStatus.UNPROCESSABLE_ENTITY.name()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<?> deleteUser(long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok("User with id: " + id + " deleted");
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User with id: " + id + " not found!", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }
}
