package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Konferencija;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.ResursEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntityPK;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;
import com.example.pisioconf_backend.controllers.repositories.ResursRepository;
import com.example.pisioconf_backend.controllers.repositories.RezervacijaRepository;
import com.example.pisioconf_backend.services.RezervacijaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Rezervacija> findAll() {
        return rezervacijaRepository.findAll().stream().map(l -> modelMapper.map(l, Rezervacija.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        rezervacijaRepository.deleteById(id);
    }

    @Override
    public Rezervacija update(Integer id, RezervacijaRequest rezervacijaRequest) {
        RezervacijaEntity rezervacijaEntity = rezervacijaRepository.findById(id).get(); // modelMapper.map(konferencijaRequest, KonferencijaEntity.class);
        if (rezervacijaRequest.getKolicina() != null) {
            rezervacijaEntity.setKolicina(rezervacijaRequest.getKolicina());
        }


      //  rezervacijaEntity.setId(id);

        rezervacijaEntity = rezervacijaRepository.saveAndFlush(rezervacijaEntity);
       // return findById(konferencijaEntity.getId());
        return null;
    }
}
