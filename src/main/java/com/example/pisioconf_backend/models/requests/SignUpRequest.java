package com.example.pisioconf_backend.models.requests;

import com.example.pisioconf_backend.models.enums.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String naziv;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Role rola;




}