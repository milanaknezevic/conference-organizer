package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Sesija;
import com.example.pisioconf_backend.models.entities.KonferencijaEntity;
import com.example.pisioconf_backend.models.entities.SesijaEntity;
import com.example.pisioconf_backend.models.requests.SesijaRequest;
import com.example.pisioconf_backend.repositories.KonferencijaRepository;
import com.example.pisioconf_backend.repositories.SesijaRepository;
import com.example.pisioconf_backend.services.SesijaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class SesijaImplService implements SesijaService {
    private final ModelMapper modelMapper;
    private final SesijaRepository sesijaRepository;
    private final KonferencijaRepository konferencijaRepository;

    public SesijaImplService(ModelMapper modelMapper, SesijaRepository sesijaRepository,
                             KonferencijaRepository konferencijaRepository) {
        this.modelMapper = modelMapper;
        this.sesijaRepository = sesijaRepository;
        this.konferencijaRepository = konferencijaRepository;
    }

    @Override
    public List<Sesija> findAll() {
        return sesijaRepository.findAll().stream().map(l -> modelMapper.map(l, Sesija.class)).collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        sesijaRepository.deleteById(id);
    }

    @Override
    public Sesija findById(Integer id) throws NotFoundException {
        return modelMapper.map(sesijaRepository.findById(id).orElseThrow(NotFoundException::new), Sesija.class);

    }

    @Override
    public Sesija insert(SesijaRequest sesijaRequest) throws NotFoundException {
        KonferencijaEntity konferencijaEntity = konferencijaRepository.findById(sesijaRequest.getKonferencijaId()).get();



        SesijaEntity sesijaEntity = modelMapper.map(sesijaRequest, SesijaEntity.class);


        sesijaEntity.setKonferencijaByKonferencijaId(konferencijaEntity);

        sesijaEntity.setId(null);

        sesijaEntity = sesijaRepository.saveAndFlush(sesijaEntity);
        return findById(sesijaEntity.getId());
    }

    @Override
    public Sesija update(Integer id, SesijaRequest sesijaRequest) throws NotFoundException {
        return null;
    }
}
