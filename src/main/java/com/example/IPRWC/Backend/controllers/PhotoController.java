package com.example.IPRWC.Backend.controllers;


import com.example.IPRWC.Backend.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/photos")

public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
    @GetMapping("/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) {
        return photoService.getFile(fileName);
    }

}
