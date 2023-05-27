package com.example.pisioconf_backend.models.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Dogadjaj {
    private Integer id;
    private Date startTime;
    private Date endTime;
    private String naziv;
    private String url;
    private Korisnik korisnik;
    private List<Posjetilac> posjetilacs;
    private Lokacija lokacija;
    private Soba soba;
    private List<Rezervacija> rezervacijas;

}