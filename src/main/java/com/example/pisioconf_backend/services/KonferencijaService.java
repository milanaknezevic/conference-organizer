package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;

import java.util.List;

public interface KonferencijaService //extends CrudService<Integer> {
{
    List<Konferencija> findAll();

    Konferencija findById(Integer id) throws NotFoundException;

    List<Konferencija> getAllKonferencijeByLocationId(Integer id);

    Konferencija insert(KonferencijaRequest konferencijaRequest) throws NotFoundException;

    Konferencija update(Integer id, KonferencijaRequest konferencijaRequest) throws NotFoundException;

    void delete(Integer id);
    List<Ocjena> getAllOcjeneByKorisnikId(Integer id);

    List<KonferencijaEntity> findAllWhereKonferencijaIsNotFinished();
}
