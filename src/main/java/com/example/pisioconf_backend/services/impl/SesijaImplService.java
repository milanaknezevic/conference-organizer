package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.Sesija;
import com.example.pisioconf_backend.controllers.repositories.SesijaRepository;
import com.example.pisioconf_backend.services.SesijaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SesijaImplService implements SesijaService {
    private final ModelMapper modelMapper;
    private final SesijaRepository sesijaRepository;

    public SesijaImplService(ModelMapper modelMapper, SesijaRepository sesijaRepository) {
        this.modelMapper = modelMapper;
        this.sesijaRepository = sesijaRepository;
    }

    @Override
    public List<Sesija> findAll() {
        return sesijaRepository.findAll().stream().map(l->modelMapper.map(l, Sesija.class)).collect(Collectors.toList());

    }

    @Override
    public void delete(Integer id) {
        sesijaRepository.deleteById(id);
    }
}
