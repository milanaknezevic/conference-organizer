package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.models.dto.Lokacija;

import java.util.List;

public interface LokacijaService {
    List<Lokacija> findAll();

//    List<Konferencija> getAllKonferencijeByLocationId(Integer id);
}
