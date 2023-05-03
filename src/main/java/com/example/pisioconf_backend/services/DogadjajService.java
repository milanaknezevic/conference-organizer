package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Dogadjaj;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.requests.DogadjajRequest;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;

import java.util.List;

public interface DogadjajService {
    List<Dogadjaj> findAll();

    Dogadjaj findById(Integer id) throws NotFoundException;

    void delete(Integer id);

    Dogadjaj insert(DogadjajRequest konferencijaRequest) throws NotFoundException;
    Dogadjaj update(Integer id, DogadjajRequest dogadjajRequest) throws NotFoundException;
}
