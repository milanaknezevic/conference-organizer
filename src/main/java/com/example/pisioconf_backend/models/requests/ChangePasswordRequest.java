package com.example.pisioconf_backend.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {

    @NotBlank
    private String password;
    @NotBlank
    private String trenutnaLozinka;
}