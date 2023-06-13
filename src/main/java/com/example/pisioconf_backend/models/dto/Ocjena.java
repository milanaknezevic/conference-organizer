package com.example.pisioconf_backend.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Ocjena {
    private Integer zvjezdica;
    private String komentar;
    private Korisnik korisnik;
    private Date datum;
}
