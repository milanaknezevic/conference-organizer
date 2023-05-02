package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.models.dto.Sesija;

import java.util.List;

public interface SesijaService {
    List<Sesija> findAll();

    void delete(Integer id);

    // List<Konferencija> getAllSesijeByKonferencijaId(Integer id);

}
