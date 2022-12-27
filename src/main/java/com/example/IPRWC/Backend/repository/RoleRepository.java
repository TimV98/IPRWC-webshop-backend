package com.example.IPRWC.Backend.repository;

import com.example.IPRWC.Backend.entities.Role;
import com.example.IPRWC.Backend.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
