package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.controllers.repositories.*;
import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Dogadjaj;
import com.example.pisioconf_backend.models.entities.DogadjajEntity;
import com.example.pisioconf_backend.models.entities.LokacijaEntity;
import com.example.pisioconf_backend.models.entities.SesijaEntity;
import com.example.pisioconf_backend.models.entities.TipDogadjajaEntity;
import com.example.pisioconf_backend.models.requests.DogadjajRequest;
import com.example.pisioconf_backend.services.DogadjajService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DogadjajImplService implements DogadjajService {
    private final ModelMapper modelMapper;
    private final DogadjajRepository dogadjajRepository;
    private final RezervacijaRepository rezervacijaRepository;
    private final LokacijaRepository lokacijaRepository;
    private final TipDogadjajaRepository tipDogadjajaRepository;
    private final SesijaRepository sesijaRepository;

    public DogadjajImplService(ModelMapper modelMapper, DogadjajRepository dogadjajRepository, RezervacijaRepository rezervacijaRepository,
                               LokacijaRepository lokacijaRepository, TipDogadjajaRepository tipDogadjajaRepository,
                               SesijaRepository sesijaRepository) {
        this.modelMapper = modelMapper;
        this.dogadjajRepository = dogadjajRepository;
        this.rezervacijaRepository = rezervacijaRepository;
        this.lokacijaRepository = lokacijaRepository;
        this.tipDogadjajaRepository = tipDogadjajaRepository;
        this.sesijaRepository = sesijaRepository;
    }

    @Override
    public List<Dogadjaj> findAll() {
        return dogadjajRepository.findAll().stream().map(l -> modelMapper.map(l, Dogadjaj.class)).collect(Collectors.toList());
    }

    @Override
    public Dogadjaj findById(Integer id) throws NotFoundException {
        return modelMapper.map(dogadjajRepository.findById(id).orElseThrow(NotFoundException::new), Dogadjaj.class);
    }

    @Override
    public void delete(Integer id) {
        dogadjajRepository.deleteById(id);
    }

    @Override
    public Dogadjaj insert(DogadjajRequest dogadjajRequest) throws NotFoundException {
        LokacijaEntity lokacijaEntity = lokacijaRepository.findById(dogadjajRequest.getLokacijaId()).get();
        TipDogadjajaEntity tipDogadjajaEntity = tipDogadjajaRepository.findById(dogadjajRequest.getTipDogadjaja()).get();
        SesijaEntity sesijaEntity = sesijaRepository.findById(dogadjajRequest.getSesijaId()).get();

        DogadjajEntity dogadjajEntity = modelMapper.map(dogadjajRequest, DogadjajEntity.class);
        dogadjajEntity.setSesijaBySesijaId(sesijaEntity);
        dogadjajEntity.setTipDogadjajaByTipDogadjajaId(tipDogadjajaEntity);
        dogadjajEntity.setLokacijaByLokacijaId(lokacijaEntity);

        dogadjajEntity.setId(null);
        dogadjajEntity = dogadjajRepository.saveAndFlush(dogadjajEntity);
        return findById(dogadjajEntity.getId());
    }
}
