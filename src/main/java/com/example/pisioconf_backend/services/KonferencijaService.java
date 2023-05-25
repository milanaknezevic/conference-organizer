package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Korisnik;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.requests.KonferencijaRequest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface KonferencijaService //extends CrudService<Integer> {
{
    List<Konferencija> findAll();
    List<Konferencija> findAllByModeratorId(Integer id) throws NotFoundException;

    Konferencija findById(Integer id) throws NotFoundException;

    List<Konferencija> getAllKonferencijeByLocationId(Integer id);

    Konferencija insert(KonferencijaRequest konferencijaRequest) throws NotFoundException;

    Konferencija update(Integer id, KonferencijaRequest konferencijaRequest) throws NotFoundException;

    void delete(Integer id);
    List<Ocjena> getAllOcjeneByKorisnikId(Integer id);

    List<KonferencijaEntity> findAllWhereKonferencijaIsNotFinished();

    List<Konferencija> findAllKonferencijeByStatus(Boolean status);
    List<Konferencija> findAllKonferencijeByDatum(LocalDateTime datum) throws NotFoundException;

    List<Konferencija> findAllKonferencijeByNaziv(String nazivPattern) throws NotFoundException;


    List<Konferencija> findAllKonferencijeByStatusAndDatum(Boolean status,LocalDateTime datum) throws NotFoundException;
    List<Konferencija> findAllKonferencijeByStatusAndNaziv(Boolean status,String nazivPattern) throws NotFoundException;
    List<Konferencija> findAllKonferencijeByDatumAndNaziv(LocalDateTime datum,String nazivPattern) throws NotFoundException;
    List<Konferencija> findAllKonferencijeByStatusAndDatumAndNaziv(Boolean status,LocalDateTime datum,String nazivPattern) throws NotFoundException;


    List<Konferencija> findAllKonferencijeByModerator(Integer idModeratora) throws NotFoundException;
    List<Konferencija> findAllKonferencijeByPosjetilac(Integer idPosjetioca) throws NotFoundException;

}
