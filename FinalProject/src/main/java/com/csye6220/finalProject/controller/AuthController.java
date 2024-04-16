package com.csye6220.finalProject.controller;

import com.csye6220.finalProject.dto.AuthResponse;
import com.csye6220.finalProject.dto.LoginRequest;
import com.csye6220.finalProject.dto.RegisterRequest;
import com.csye6220.finalProject.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        super();
        this.authService = authService;
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        System.out.println("inside signup");
        authService.signup(registerRequest);
        return new ResponseEntity<>("Registeration Sucessfull ", HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        String token = authService.login(loginRequest);
        return new ResponseEntity<>(new AuthResponse(token),HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")){
            String actualToken = token.substring(7);
            //Logic to blacklist the token
        }
        return ResponseEntity.ok().build();
    }
}