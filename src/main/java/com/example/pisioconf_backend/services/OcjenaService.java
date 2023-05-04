package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntityPK;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.requests.OcjenaRequest;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;

import java.util.List;

public interface OcjenaService {
    OcjenaEntity insert(OcjenaRequest ocjenaRequest) throws NotFoundException;
    List<Ocjena> findAll();


    Ocjena update(OcjenaEntityPK id, OcjenaRequest ocjenaRequest)throws NotFoundException;

    void delete(OcjenaEntityPK id);
}
