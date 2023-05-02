package com.example.pisioconf_backend.services;

import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;

import java.util.List;


public interface KorisnikService {

    KorisnikEntity findById(Integer id);
    List<Konferencija> getKonferencijeZaModeratora(Integer idModeratora);
    List<Konferencija> getKonferencijeZaOrganizatora(Integer idOrganizatora);
}
