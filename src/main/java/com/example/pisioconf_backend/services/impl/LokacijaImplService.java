package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.services.LokacijaService;
import com.example.pisioconf_backend.models.dto.Lokacija;
import com.example.pisioconf_backend.repositories.LokacijaRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LokacijaImplService implements LokacijaService {

    private final LokacijaRepository lokacijaRepository;
    private final ModelMapper modelMapper;

    public LokacijaImplService(LokacijaRepository lokacijaRepository, ModelMapper modelMapper) {
        this.lokacijaRepository = lokacijaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Lokacija> findAll() {
        return lokacijaRepository.findAll().stream().map(l -> modelMapper.map(l, Lokacija.class)).collect(Collectors.toList());
    }


}
