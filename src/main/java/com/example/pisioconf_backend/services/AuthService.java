package com.example.pisioconf_backend.services;


import com.example.pisioconf_backend.models.dto.LoginResponse;
import com.example.pisioconf_backend.models.requests.LoginRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}
