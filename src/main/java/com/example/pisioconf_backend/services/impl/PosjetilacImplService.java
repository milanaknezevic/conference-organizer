package com.example.pisioconf_backend.services.impl;

import com.example.pisioconf_backend.controllers.repositories.PosjetilacRepository;
import com.example.pisioconf_backend.exception.NotFoundException;
import com.example.pisioconf_backend.models.dto.Ocjena;
import com.example.pisioconf_backend.models.dto.Posjetilac;
import com.example.pisioconf_backend.models.entities.OcjenaEntity;
import com.example.pisioconf_backend.models.entities.OcjenaEntityPK;
import com.example.pisioconf_backend.models.entities.PosjetilacEntity;
import com.example.pisioconf_backend.models.entities.PosjetilacEntityPK;
import com.example.pisioconf_backend.models.requests.PosjetilacRequest;
import com.example.pisioconf_backend.services.PosjetilacService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PosjetilacImplService implements PosjetilacService {
    private final ModelMapper modelMapper;
    private final PosjetilacRepository posjetilacRepository;

    public PosjetilacImplService(ModelMapper modelMapper, PosjetilacRepository posjetilacRepository) {
        this.modelMapper = modelMapper;
        this.posjetilacRepository = posjetilacRepository;
    }

    @Override
    public PosjetilacEntity insert(PosjetilacRequest posjetilacRequest) throws NotFoundException {
        PosjetilacEntityPK posjetilacEntityPK = new PosjetilacEntityPK();
        posjetilacEntityPK.setKorisnikId(posjetilacRequest.getKorisnikId());
        posjetilacEntityPK.setDogadjajId(posjetilacRequest.getDogadjajId());
        PosjetilacEntity posjetilacEntity = modelMapper.map(posjetilacRequest, PosjetilacEntity.class);
        posjetilacEntity.setId(posjetilacEntityPK);
        posjetilacEntity = posjetilacRepository.saveAndFlush(posjetilacEntity);
//        ResursEntity resursEntity = resursRepository.findById(rezervacijaRequest.getResursId()).get();
//        resursEntity.setKolicina(resursEntity.getKolicina()-rezervacijaRequest.getKolicina());
//        resursEntity= resursRepository.saveAndFlush(resursEntity);
        return null;
    }

    @Override
    public List<Posjetilac> findAll() {
        return posjetilacRepository.findAll().stream().map(l -> modelMapper.map(l, Posjetilac.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(PosjetilacEntityPK id) {
        posjetilacRepository.deleteById(id);
    }
}
