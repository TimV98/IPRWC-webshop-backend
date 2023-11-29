package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface PhotosRepository extends JpaRepository<Photo, Long> {

     Optional<Photo> findByName(String fileName);
}
