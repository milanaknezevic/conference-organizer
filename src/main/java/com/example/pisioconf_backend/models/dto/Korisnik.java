package com.example.pisioconf_backend.models.dto;

import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.example.pisioconf_backend.models.enums.Role;
import lombok.Data;

@Data
public class Korisnik {

    private Integer id;
    private String naziv;
    private Role rola;
    private String email;
    private String username;
    private String password;
    private KorisnikEntity.Status status;
}
