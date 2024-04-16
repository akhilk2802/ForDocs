package com.csye6220.finalProject.service.impl;

import com.csye6220.finalProject.dao.UserDAO;
import com.csye6220.finalProject.dto.LoginRequest;
import com.csye6220.finalProject.dto.RegisterRequest;
import com.csye6220.finalProject.model.User;
import com.csye6220.finalProject.security.JwtProvider;
import com.csye6220.finalProject.service.AuthService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private  final PasswordEncoder passwordEncoder;
    private final UserDAO userDAO;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserDAO userDAO, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.userDAO = userDAO;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(Date.from(Instant.now()));
        userDAO.addUser(user);

    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        return jwtProvider.generateToken(authenticationResponse);
    }
}
