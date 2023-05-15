package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Sesija;
import com.example.pisioconf_backend.models.requests.SesijaRequest;

import java.util.List;

public interface SesijaService {
    List<Sesija> findAll();

    void delete(Integer id);

    Sesija findById(Integer id) throws NotFoundException;

   // List<Sesija> getAllKonferencijeByLocationId(Integer id);

    Sesija insert(SesijaRequest sesijaRequest) throws NotFoundException;

    Sesija update(Integer id, SesijaRequest sesijaRequest) throws NotFoundException;



    // List<Konferencija> getAllSesijeByKonferencijaId(Integer id);

}
