package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Photo;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.PhotosRepository;
import com.example.IPRWC.Backend.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class PhotoService {

    @Autowired
    private PhotosRepository photosRepository;

    public ResponseEntity<?> store(MultipartFile file) throws IOException {
        if (file == null) {
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, "File wasn't added").getHttpStatus());
        }
        photosRepository.save(Photo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(ImageUtils.compressImage(file.getBytes())).build());

        return new ResponseEntity<>(new MessageResponse("File successfully added: " + file.getOriginalFilename()), HttpStatus.OK);
    }

    public ResponseEntity<?> getFile(String fileName) {
        if (photosRepository.findByName(fileName).isPresent()) {
            Photo dbPhoto = photosRepository.findByName(fileName).get();
            byte[] image = ImageUtils.decompressImage(dbPhoto.getData());
            return new ResponseEntity<>(image, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "File with name " + fileName + "not found"), HttpStatus.NOT_FOUND);
    }
}
