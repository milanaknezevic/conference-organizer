package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.models.dto.Soba;
import com.example.pisioconf_backend.models.dto.TipDogadjaja;
import com.example.pisioconf_backend.repositories.SobaRepository;
import com.example.pisioconf_backend.services.SobaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SobaImplService implements SobaService {
    private final ModelMapper modelMapper;
    private final SobaRepository sobaRepository;

    public SobaImplService(ModelMapper modelMapper, SobaRepository sobaRepository) {
        this.modelMapper = modelMapper;
        this.sobaRepository = sobaRepository;
    }
    @Override
    public List<Soba> findAllByLokacijaId(Integer lokacija)
    {
        return sobaRepository.findAllByLokacija_Id(lokacija).stream().map(l->modelMapper.map(l, Soba.class)).collect(Collectors.toList());

    }

}
