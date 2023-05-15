package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.TipDogadjaja;
import com.example.pisioconf_backend.repositories.TipDogadjajaRepository;
import com.example.pisioconf_backend.services.TipDogadjajaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class TipDogadjajaImplService implements TipDogadjajaService {
    private final ModelMapper modelMapper;
    private final TipDogadjajaRepository tipDogadjajaRepository;

    public TipDogadjajaImplService(ModelMapper modelMapper, TipDogadjajaRepository tipDogadjajaRepository) {
        this.modelMapper = modelMapper;
        this.tipDogadjajaRepository = tipDogadjajaRepository;
    }

    @Override
    public List<TipDogadjaja> findAll() {
        return tipDogadjajaRepository.findAll().stream().map(l->modelMapper.map(l, TipDogadjaja.class)).collect(Collectors.toList());
    }
}
