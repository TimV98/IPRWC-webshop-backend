package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.entities.Photo;
import com.example.IPRWC.Backend.payload.response.ErrorResponse;
import com.example.IPRWC.Backend.payload.response.MessageResponse;
import com.example.IPRWC.Backend.repository.PhotosRepository;
import com.example.IPRWC.Backend.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    public ResponseEntity<?> getFile(String fileName) {
        if (photosRepository.findByName(fileName).isPresent()) {
            Photo dbPhoto = photosRepository.findByName(fileName).get();
            byte[] image = ImageUtils.decompressImage(dbPhoto.getData());
            if (dbPhoto.getType().equals(MediaType.IMAGE_PNG_VALUE)) {
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(image);

            } else if (dbPhoto.getType().equals(MediaType.IMAGE_JPEG_VALUE)) {
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_JPEG).body(image);

            }
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Wrong Media Type"));
        }
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "File with name " + fileName + "not found", HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    public Photo getFileInformation(long id){
        return photosRepository.findById(id).get();
    }
}
