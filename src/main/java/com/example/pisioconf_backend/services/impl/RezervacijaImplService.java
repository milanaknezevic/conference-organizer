package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.DogadjajEntity;
import com.example.pisioconf_backend.models.entities.ResursEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntity;
import com.example.pisioconf_backend.models.entities.RezervacijaEntityPK;
import com.example.pisioconf_backend.models.requests.RezervacijaRequest;
import com.example.pisioconf_backend.repositories.DogadjajRepository;
import com.example.pisioconf_backend.repositories.ResursRepository;
import com.example.pisioconf_backend.repositories.RezervacijaRepository;
import com.example.pisioconf_backend.services.RezervacijaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RezervacijaImplService implements RezervacijaService {
    private final ModelMapper modelMapper;
    private final RezervacijaRepository rezervacijaRepository;
    private final ResursRepository resursRepository;
    private final DogadjajRepository dogadjajRepository;

    public RezervacijaImplService(ModelMapper modelMapper, RezervacijaRepository rezervacijaRepository, ResursRepository resursRepository,
                                  DogadjajRepository dogadjajRepository) {
        this.modelMapper = modelMapper;
        this.rezervacijaRepository = rezervacijaRepository;
        this.resursRepository = resursRepository;
        this.dogadjajRepository = dogadjajRepository;
    }

    @Override
    public RezervacijaEntity insert(RezervacijaRequest rezervacijaRequest) throws NotFoundException {
        RezervacijaEntityPK rezervacijaEntityPK = new RezervacijaEntityPK();
        rezervacijaEntityPK.setDogadjajId(rezervacijaRequest.getDogadjajId());
        rezervacijaEntityPK.setResursId(rezervacijaRequest.getResursId());
        RezervacijaEntity rezervacijaEntity = modelMapper.map(rezervacijaRequest, RezervacijaEntity.class);
        rezervacijaEntity.setId(rezervacijaEntityPK);

        ResursEntity resurs = resursRepository.findById(rezervacijaRequest.getResursId()).orElse(null);
        DogadjajEntity dogadjaj = dogadjajRepository.findById(rezervacijaRequest.getDogadjajId()).orElse(null);

        if (resurs != null && dogadjaj != null) {
            rezervacijaEntity.setResursByResursId(resurs);
            rezervacijaEntity.setDogadjajByDogadjajId(dogadjaj);
        }
        if (rezervacijaRequest.getKolicina() > resurs.getKolicina()) {
            throw new NotFoundException();//nije not found vec trebam napraviti novi exc
        }
        //smanjiti u resursu uzete resurse
        resurs.setKolicina(resurs.getKolicina() - rezervacijaRequest.getKolicina());
        resurs = resursRepository.saveAndFlush(resurs);

        rezervacijaEntity = rezervacijaRepository.saveAndFlush(rezervacijaEntity);

        return null;
    }

    @Override
    public List<Rezervacija> findAll() {
        return rezervacijaRepository.findAll().stream().map(l -> modelMapper.map(l, Rezervacija.class)).collect(Collectors.toList());
    }

    @Override
    public List<RezervacijaEntity> findAllByDogadjajId(Integer id) throws NotFoundException {
        return rezervacijaRepository.getAllRezervacijeByDogadjajID(id);

    }


    @Override
    public void delete(RezervacijaEntityPK id) {
        rezervacijaRepository.deleteById(id);
    }

    @Override
    public Rezervacija findById(RezervacijaEntityPK id) {
        return modelMapper.map(rezervacijaRepository.findById(id).orElseThrow(NotFoundException::new), Rezervacija.class);
    }

    @Override
    public Rezervacija update(RezervacijaEntityPK id, RezervacijaRequest rezervacijaRequest) {
        RezervacijaEntity rezervacijaEntity = rezervacijaRepository.findById(id).get();
        if (rezervacijaRequest.getKolicina() != null) {
            rezervacijaEntity.setKolicina(rezervacijaRequest.getKolicina());
        }

        rezervacijaEntity.setId(id);
        rezervacijaEntity = rezervacijaRepository.saveAndFlush(rezervacijaEntity);
        return null; // findById(ocjenaEntity.getId());
    }


}
