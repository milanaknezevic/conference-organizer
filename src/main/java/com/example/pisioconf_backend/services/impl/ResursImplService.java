package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.Resurs;
import com.example.pisioconf_backend.models.dto.Rezervacija;
import com.example.pisioconf_backend.models.entities.ResursEntity;
import com.example.pisioconf_backend.repositories.ResursRepository;
import com.example.pisioconf_backend.services.ResursService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResursImplService implements ResursService {
    private final ModelMapper modelMapper;
    private final ResursRepository resursRepository;

    public ResursImplService(ModelMapper modelMapper, ResursRepository resursRepository) {
        this.modelMapper = modelMapper;
        this.resursRepository = resursRepository;
    }

    @Override
    public List<Resurs> findAll() {
        return resursRepository.findAll().stream().map(l -> modelMapper.map(l, Resurs.class)).collect(Collectors.toList());
    }

    @Override
    public List<Resurs> findALlByLokacija(Integer idLokacije) {
        return resursRepository.findAllByLokacija_Id(idLokacije).stream().map(l -> modelMapper.map(l, Resurs.class)).collect(Collectors.toList());

    }

    @Override
    public void updateResursKolicinaPovecaj(Integer resursId, Integer novaKolicina) {
        ResursEntity resurs = resursRepository.findById(resursId).get();

        Integer kol=resurs.getKolicina()+novaKolicina;
            resurs.setKolicina(kol);
            resursRepository.saveAndFlush(resurs);

    }
    @Override
    public void updateResursKolicinaSmanji(Integer resursId, Integer novaKolicina) {
        ResursEntity resurs = resursRepository.findById(resursId).get();

        Integer kol=resurs.getKolicina()-novaKolicina;
        resurs.setKolicina(kol);
        resursRepository.saveAndFlush(resurs);

    }
}
