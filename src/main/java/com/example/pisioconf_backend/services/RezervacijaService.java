package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;

public interface RezervacijaService {
    RezervacijaEntity insert(RezervacijaRequest rezervacijaRequest) throws NotFoundException;
}
