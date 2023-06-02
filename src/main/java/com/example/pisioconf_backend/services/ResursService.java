package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.models.dto.Resurs;
import com.example.pisioconf_backend.models.dto.Rezervacija;

import java.util.List;

public interface ResursService {
    List<Resurs> findAll();
    List<Resurs> findALlByLokacija(Integer idLokacije);
     void updateResursKolicinaPovecaj(Integer resursId, Integer novaKolicina);
     void updateResursKolicinaSmanji(Integer resursId, Integer novaKolicina);
}
