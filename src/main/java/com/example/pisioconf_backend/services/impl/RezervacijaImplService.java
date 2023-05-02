package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.entities.ResursEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntityPK;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;
import com.example.pisioconf_backend.controllers.repositories.ResursRepository;
import com.example.pisioconf_backend.controllers.repositories.RezervacijaRepository;
import com.example.pisioconf_backend.services.RezervacijaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RezervacijaImplService implements RezervacijaService {
    private final ModelMapper modelMapper;
    private final RezervacijaRepository rezervacijaRepository;
    private final ResursRepository resursRepository;

    public RezervacijaImplService(ModelMapper modelMapper, RezervacijaRepository rezervacijaRepository, ResursRepository resursRepository) {
        this.modelMapper = modelMapper;
        this.rezervacijaRepository = rezervacijaRepository;
        this.resursRepository = resursRepository;
    }

    @Override
    public RezervacijaEntity insert(RezervacijaRequest rezervacijaRequest) throws NotFoundException {
        RezervacijaEntityPK rezervacijaEntityPK = new RezervacijaEntityPK();
        rezervacijaEntityPK.setDogadjajId(rezervacijaRequest.getDogadjajId());
        rezervacijaEntityPK.setResursId(rezervacijaRequest.getResursId());
        RezervacijaEntity rezervacijaEntity = modelMapper.map(rezervacijaRequest, RezervacijaEntity.class);
        rezervacijaEntity.setId(rezervacijaEntityPK);
        rezervacijaEntity = rezervacijaRepository.saveAndFlush(rezervacijaEntity);
        ResursEntity resursEntity = resursRepository.findById(rezervacijaRequest.getResursId()).get();
        resursEntity.setKolicina(resursEntity.getKolicina()-rezervacijaRequest.getKolicina());
        resursEntity= resursRepository.saveAndFlush(resursEntity);
        return null;
    }
}
