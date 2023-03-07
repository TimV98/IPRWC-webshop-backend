package com.example.IPRWC.Backend.services;

import com.example.IPRWC.Backend.payload.request.LoginRequest;
import com.example.IPRWC.Backend.payload.response.JwtResponse;
import com.example.IPRWC.Backend.security.JwtUtil;
import com.example.IPRWC.Backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


}
