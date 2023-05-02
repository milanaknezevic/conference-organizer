package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.entities.KorisnikEntity;
import com.example.pisioconf_backend.controllers.repositories.KorisnikRepository;
import com.example.pisioconf_backend.services.KorisnikService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class KorisnikImplService implements KorisnikService {
    public final KorisnikRepository korisnikRepository;
    private final ModelMapper modelMapper;

    public KorisnikImplService(KorisnikRepository korisnikRepository, ModelMapper modelMapper) {
        this.korisnikRepository = korisnikRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public KorisnikEntity findById(Integer id) {
        return korisnikRepository.findById(id).get();
    }

    @Override
    public List<Konferencija> getKonferencijeZaModeratora(Integer id) {
        return korisnikRepository.getAllByKorisnikByModeratorId(id).stream().map(l -> modelMapper.map(l, Konferencija.class)).collect(Collectors.toList());
    }

    @Override
    public List<Konferencija> getKonferencijeZaOrganizatora(Integer id) {
        return korisnikRepository.getAllByKorisnikByOrganizatorId(id).stream().map(l->modelMapper.map(l,Konferencija.class)).collect(Collectors.toList());
    }

}
