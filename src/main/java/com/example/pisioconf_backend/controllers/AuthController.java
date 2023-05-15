package com.example.pisioconf_backend.controllers;

import com.example.pisioconf_backend.models.dto.JwtUser;
import com.example.pisioconf_backend.models.dto.LoginResponse;
import com.example.pisioconf_backend.models.requests.LoginRequest;
import com.example.pisioconf_backend.models.requests.SignUpRequest;
import com.example.pisioconf_backend.services.AuthService;
import com.example.pisioconf_backend.services.KorisnikService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import javax.validation.Valid;


@RestController
public class AuthController {

    private final AuthService service;
    private final KorisnikService korisnikService;

    public AuthController(AuthService service, KorisnikService korisnikService) {
        this.service = service;
        this.korisnikService = korisnikService;
    }


    @PostMapping("login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request)
    {
        return service.login(request);
    }

    @GetMapping("state")
    public LoginResponse state(Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        return korisnikService.findById(jwtUser.getId(), LoginResponse.class);
    }

    @PostMapping("sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest request)
    {
        korisnikService.signUp(request);
    }
}
