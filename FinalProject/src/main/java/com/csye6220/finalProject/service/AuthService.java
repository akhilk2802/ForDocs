package com.csye6220.finalProject.service;

import com.csye6220.finalProject.dto.LoginRequest;
import com.csye6220.finalProject.dto.RegisterRequest;
import com.csye6220.finalProject.model.User;

public interface AuthService {

    void signup(RegisterRequest registerRequest);
    String login(LoginRequest loginRequest);
}